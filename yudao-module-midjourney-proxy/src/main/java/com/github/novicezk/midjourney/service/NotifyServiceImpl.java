package com.github.novicezk.midjourney.service;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.ProxyProperties;
import com.github.novicezk.midjourney.enums.TaskStatus;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
public class NotifyServiceImpl implements NotifyService {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private final ThreadPoolExecutor executor;
	private final TimedCache<String, String> taskStatusMap = CacheUtil.newTimedCache(Duration.ofHours(1).toMillis());

	public NotifyServiceImpl(ProxyProperties properties) {
		this.executor = ThreadPoolUtils.newFixedThreadPool("TaskNotify-", properties.getNotifyPoolSize());
	}

	@Override
	public synchronized void notifyTaskChange(Task task) {
		String notifyHook = task.getPropertyGeneric(Constants.TASK_PROPERTY_NOTIFY_HOOK);
		if (CharSequenceUtil.isBlank(notifyHook)) {
			return;
		}
		String taskId = task.getId();
		String statusStr = task.getStatus() + ":" + task.getProgress();
		log.trace("Wait notify task change, task: {}({}), hook: {}", taskId, statusStr, notifyHook);
		try {
			String paramsStr = OBJECT_MAPPER.writeValueAsString(task);
			this.executor.execute(() -> {
				try {
					executeNotify(taskId, statusStr, notifyHook, paramsStr);
				} catch (Exception e) {
					log.warn("Notify task change error, task: {}({}), hook: {}, msg: {}", taskId, statusStr, notifyHook, e.getMessage());
				}
			});
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void executeNotify(String taskId, String currentStatusStr, String notifyHook, String paramsStr) {
		synchronized (this.taskStatusMap) {
			String existStatusStr = this.taskStatusMap.get(taskId, () -> currentStatusStr);
			int compare = compareStatusStr(currentStatusStr, existStatusStr);
			if (compare < 0) {
				log.debug("Ignore this change, task: {}({})", taskId, currentStatusStr);
				return;
			}
			this.taskStatusMap.put(taskId, currentStatusStr);
		}
		log.debug("推送任务变更, 任务: {}({}), hook: {}", taskId, currentStatusStr, notifyHook);
		ResponseEntity<String> responseEntity = postJson(notifyHook, paramsStr);
		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			log.warn("Notify task change fail, task: {}({}), hook: {}, code: {}, msg: {}", taskId, currentStatusStr, notifyHook, responseEntity.getStatusCodeValue(), responseEntity.getBody());
		}
	}

	private ResponseEntity<String> postJson(String notifyHook, String paramsJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(paramsJson, headers);
		return new RestTemplate().postForEntity(notifyHook, httpEntity, String.class);
	}

	private int compareStatusStr(String statusStr1, String statusStr2) {
		if (CharSequenceUtil.equals(statusStr1, statusStr2)) {
			return 0;
		}
		float o1 = convertOrder(statusStr1);
		float o2 = convertOrder(statusStr2);
		return CompareUtil.compare(o1, o2);
	}

	private float convertOrder(String statusStr) {
		String[] split = statusStr.split(":");
		TaskStatus status = TaskStatus.valueOf(split[0]);
		if (status != TaskStatus.IN_PROGRESS || split.length == 1) {
			return status.getOrder();
		}
		String progress = split[1];
		if (progress.endsWith("%")) {
			return status.getOrder() + Float.parseFloat(progress.substring(0, progress.length() - 1)) / 100;
		} else {
			return status.getOrder();
		}
	}

}
