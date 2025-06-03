package com.github.novicezk.midjourney.controller;

import com.github.novicezk.midjourney.domain.DiscordAccount;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.loadbalancer.DiscordLoadBalancer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "账号查询")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
	private final DiscordLoadBalancer loadBalancer;

	@ApiOperation(value = "指定ID获取账号")
	@GetMapping("/{id}/fetch")
	public DiscordAccount fetch(@ApiParam(value = "账号ID") @PathVariable String id) {
		DiscordInstance instance = this.loadBalancer.getDiscordInstance(id);
		return instance == null ? null : instance.account();
	}

	@ApiOperation(value = "查询所有账号")
	@GetMapping("/list")
	public List<DiscordAccount> list() {
		return this.loadBalancer.getAllInstances().stream().map(DiscordInstance::account).toList();
	}
}