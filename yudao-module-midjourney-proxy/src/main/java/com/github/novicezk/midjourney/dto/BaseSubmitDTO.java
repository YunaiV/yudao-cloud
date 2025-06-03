package com.github.novicezk.midjourney.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseSubmitDTO {

	@ApiModelProperty("自定义参数")
	protected String state;

	@ApiModelProperty("回调地址, 为空时使用全局notifyHook")
	protected String notifyHook;
}
