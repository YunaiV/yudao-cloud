package com.github.novicezk.midjourney.support;

import com.github.novicezk.midjourney.enums.TaskStatus;
import com.github.novicezk.midjourney.loadbalancer.DiscordLoadBalancer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskTimeoutSchedule {
	private final DiscordLoadBalancer discordLoadBalancer;

	@Scheduled(fixedRate = 30000L)
	public void checkTasks() {
		this.discordLoadBalancer.getAliveInstances().forEach(instance -> {
			long timeout = TimeUnit.MINUTES.toMillis(instance.account().getTimeoutMinutes());
			List<Task> tasks = instance.getRunningTasks().stream()
					.filter(t -> t.getStartTime() != null && System.currentTimeMillis() - t.getStartTime() > timeout)
					.toList();
			for (Task task : tasks) {
				if (Set.of(TaskStatus.FAILURE, TaskStatus.SUCCESS).contains(task.getStatus())) {
					log.warn("[{}] - task status is failure/success but is in the queue, end it. id: {}", instance.account().getDisplay(), task.getId());
				} else {
					log.debug("[{}] - task timeout, id: {}", instance.account().getDisplay(), task.getId());
					task.fail("任务超时");
				}
				instance.exitTask(task);
			}
		});
	}
}
