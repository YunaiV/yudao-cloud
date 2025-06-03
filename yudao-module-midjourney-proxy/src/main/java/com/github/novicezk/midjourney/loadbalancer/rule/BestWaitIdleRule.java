package com.github.novicezk.midjourney.loadbalancer.rule;

import cn.hutool.core.util.RandomUtil;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 最少等待空闲.
 * 选择等待数最少的实例，如果都不需要等待，则随机选择
 */
public class BestWaitIdleRule implements IRule {

	@Override
	public DiscordInstance choose(List<DiscordInstance> instances) {
		if (instances.isEmpty()) {
			return null;
		}
		Map<Integer, List<DiscordInstance>> map = instances.stream()
				.collect(Collectors.groupingBy(i -> {
					int wait = i.getRunningFutures().size() - i.account().getCoreSize();
					return wait >= 0 ? wait : -1;
				}));
		List<DiscordInstance> instanceList = map.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getKey)).orElseThrow().getValue();
		return RandomUtil.randomEle(instanceList);
	}

}
