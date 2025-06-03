package com.github.novicezk.midjourney.loadbalancer.rule;

import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;

import java.util.List;

public interface IRule {

	DiscordInstance choose(List<DiscordInstance> instances);
}
