package com.github.novicezk.midjourney.loadbalancer.rule;

import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询.
 */
public class RoundRobinRule implements IRule {
	private final AtomicInteger position = new AtomicInteger(0);

	@Override
	public DiscordInstance choose(List<DiscordInstance> instances) {
		if (instances.isEmpty()) {
			return null;
		}
		int pos = incrementAndGet();
		return instances.get(pos % instances.size());
	}

	private int incrementAndGet() {
		int current;
		int next;
		do {
			current = this.position.get();
			next = current == Integer.MAX_VALUE ? 0 : current + 1;
		} while (!this.position.compareAndSet(current, next));
		return next;
	}
}
