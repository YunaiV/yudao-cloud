package com.github.novicezk.midjourney.loadbalancer;


import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.domain.DiscordAccount;
import com.github.novicezk.midjourney.result.Message;
import com.github.novicezk.midjourney.result.SubmitResultVO;
import com.github.novicezk.midjourney.service.DiscordService;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.support.TaskCondition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface DiscordInstance extends DiscordService {

	String getInstanceId();

	DiscordAccount account();

	boolean isAlive();

	void startWss() throws Exception;

	List<Task> getRunningTasks();

	List<Task> getQueueTasks();

	void exitTask(Task task);

	Map<String, Future<?>> getRunningFutures();

	SubmitResultVO submitTask(Task task, Callable<Message<Void>> discordSubmit);

	default Stream<Task> findRunningTask(Predicate<Task> condition) {
		return getRunningTasks().stream().filter(condition);
	}

	default Task getRunningTask(String id) {
		return getRunningTasks().stream().filter(t -> id.equals(t.getId())).findFirst().orElse(null);
	}

	default Task getRunningTaskByNonce(String nonce) {
		if (CharSequenceUtil.isBlank(nonce)) {
			return null;
		}
		TaskCondition condition = new TaskCondition().setNonce(nonce);
		return findRunningTask(condition).findFirst().orElse(null);
	}

}
