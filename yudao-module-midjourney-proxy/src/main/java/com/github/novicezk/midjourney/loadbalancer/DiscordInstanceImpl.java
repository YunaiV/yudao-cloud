package com.github.novicezk.midjourney.loadbalancer;


import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.ReturnCode;
import com.github.novicezk.midjourney.domain.DiscordAccount;
import com.github.novicezk.midjourney.enums.BlendDimensions;
import com.github.novicezk.midjourney.enums.TaskStatus;
import com.github.novicezk.midjourney.result.Message;
import com.github.novicezk.midjourney.result.SubmitResultVO;
import com.github.novicezk.midjourney.service.DiscordService;
import com.github.novicezk.midjourney.service.DiscordServiceImpl;
import com.github.novicezk.midjourney.service.NotifyService;
import com.github.novicezk.midjourney.service.TaskStoreService;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.util.ThreadPoolUtils;
import com.github.novicezk.midjourney.wss.WebSocketStarter;
import eu.maxschuster.dataurl.DataUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class DiscordInstanceImpl implements DiscordInstance {
	private final DiscordAccount account;
	private final WebSocketStarter socketStarter;
	private final DiscordService service;
	private final TaskStoreService taskStoreService;
	private final NotifyService notifyService;

	private final ThreadPoolExecutor taskExecutor;
	private final ThreadPoolExecutor asyncSaveExecutor;
	private final List<Task> runningTasks;
	private final List<Task> queueTasks;
	private final Map<String, Future<?>> taskFutureMap = Collections.synchronizedMap(new HashMap<>());

	public DiscordInstanceImpl(DiscordAccount account, WebSocketStarter socketStarter, RestTemplate restTemplate,
			TaskStoreService taskStoreService, NotifyService notifyService, Map<String, String> paramsMap) {
		this.account = account;
		this.socketStarter = socketStarter;
		this.taskStoreService = taskStoreService;
		this.notifyService = notifyService;
		this.service = new DiscordServiceImpl(account, restTemplate, paramsMap);
		this.runningTasks = new CopyOnWriteArrayList<>();
		this.queueTasks = new CopyOnWriteArrayList<>();
		this.taskExecutor = ThreadPoolUtils.newThreadPoolExecutor("TaskQueue-", account.getCoreSize(), account.getCoreSize(), account.getQueueSize());
		this.asyncSaveExecutor = ThreadPoolUtils.newFixedThreadPool("AsyncSaveTask-", account.getCoreSize() * 4);
	}

	@Override
	public String getInstanceId() {
		return this.account.getChannelId();
	}

	@Override
	public DiscordAccount account() {
		return this.account;
	}

	@Override
	public boolean isAlive() {
		return this.account.isEnable();
	}

	@Override
	public void startWss() throws Exception {
		this.socketStarter.start();
	}

	@Override
	public List<Task> getRunningTasks() {
		return this.runningTasks;
	}

	@Override
	public List<Task> getQueueTasks() {
		return this.queueTasks;
	}

	@Override
	public void exitTask(Task task) {
		try {
			Future<?> future = this.taskFutureMap.get(task.getId());
			if (future != null) {
				future.cancel(true);
			}
			saveAndNotify(task);
		} finally {
			this.runningTasks.remove(task);
			this.queueTasks.remove(task);
			this.taskFutureMap.remove(task.getId());
		}
	}

	@Override
	public Map<String, Future<?>> getRunningFutures() {
		return this.taskFutureMap;
	}

	@Override
	public synchronized SubmitResultVO submitTask(Task task, Callable<Message<Void>> discordSubmit) {
		this.taskStoreService.save(task);
		int currentWaitNumbers;
		try {
			currentWaitNumbers = this.taskExecutor.getQueue().size();
			Future<?> future = this.taskExecutor.submit(() -> executeTask(task, discordSubmit));
			this.taskFutureMap.put(task.getId(), future);
			this.queueTasks.add(task);
		} catch (RejectedExecutionException e) {
			this.taskStoreService.delete(task.getId());
			return SubmitResultVO.fail(ReturnCode.QUEUE_REJECTED, "队列已满，请稍后尝试")
					.setProperty(Constants.TASK_PROPERTY_DISCORD_INSTANCE_ID, this.getInstanceId());
		} catch (Exception e) {
			log.error("submit task error", e);
			return SubmitResultVO.fail(ReturnCode.FAILURE, "提交失败，系统异常")
					.setProperty(Constants.TASK_PROPERTY_DISCORD_INSTANCE_ID, this.getInstanceId());
		}
		if (currentWaitNumbers == 0) {
			return SubmitResultVO.of(ReturnCode.SUCCESS, "提交成功", task.getId())
					.setProperty(Constants.TASK_PROPERTY_DISCORD_INSTANCE_ID, this.getInstanceId());
		} else {
			return SubmitResultVO.of(ReturnCode.IN_QUEUE, "排队中，前面还有" + currentWaitNumbers + "个任务", task.getId())
					.setProperty("numberOfQueues", currentWaitNumbers)
					.setProperty(Constants.TASK_PROPERTY_DISCORD_INSTANCE_ID, this.getInstanceId());
		}
	}

	private void executeTask(Task task, Callable<Message<Void>> discordSubmit) {
		this.runningTasks.add(task);
		try {
			Message<Void> result = discordSubmit.call();
			task.setStartTime(System.currentTimeMillis());
			if (result.getCode() != ReturnCode.SUCCESS) {
				task.fail(result.getDescription());
				saveAndNotify(task);
				log.debug("[{}] task finished, id: {}, status: {}", this.account.getDisplay(), task.getId(), task.getStatus());
				return;
			}
			task.setStatus(TaskStatus.SUBMITTED);
			task.setProgress("0%");
			asyncSaveAndNotify(task);
			do {
				task.sleep();
				asyncSaveAndNotify(task);
			} while (task.getStatus() == TaskStatus.IN_PROGRESS);
			log.debug("[{}] task finished, id: {}, status: {}", this.account.getDisplay(), task.getId(), task.getStatus());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			log.error("[{}] task execute error, id: {}", this.account.getDisplay(), task.getId(), e);
			task.fail("[Internal Server Error] " + e.getMessage());
			saveAndNotify(task);
		} finally {
			this.runningTasks.remove(task);
			this.queueTasks.remove(task);
			this.taskFutureMap.remove(task.getId());
		}
	}

	private void asyncSaveAndNotify(Task task) {
		this.asyncSaveExecutor.execute(() -> saveAndNotify(task));
	}

	private void saveAndNotify(Task task) {
		this.taskStoreService.save(task);
		this.notifyService.notifyTaskChange(task);
	}

	@Override
	public Message<Void> imagine(String prompt, String nonce) {
		return this.service.imagine(prompt, nonce);
	}

	@Override
	public Message<Void> upscale(String messageId, int index, String messageHash, int messageFlags, String nonce) {
		return this.service.upscale(messageId, index, messageHash, messageFlags, nonce);
	}

	@Override
	public Message<Void> variation(String messageId, int index, String messageHash, int messageFlags, String nonce) {
		return this.service.variation(messageId, index, messageHash, messageFlags, nonce);
	}

	@Override
	public Message<Void> reroll(String messageId, String messageHash, int messageFlags, String nonce) {
		return this.service.reroll(messageId, messageHash, messageFlags, nonce);
	}

	@Override
	public Message<Void> describe(String finalFileName, String nonce) {
		return this.service.describe(finalFileName, nonce);
	}

	@Override
	public Message<Void> blend(List<String> finalFileNames, BlendDimensions dimensions, String nonce) {
		return this.service.blend(finalFileNames, dimensions, nonce);
	}

	@Override
	public Message<String> upload(String fileName, DataUrl dataUrl) {
		return this.service.upload(fileName, dataUrl);
	}

	@Override
	public Message<String> sendImageMessage(String content, String finalFileName) {
		return this.service.sendImageMessage(content, finalFileName);
	}

}
