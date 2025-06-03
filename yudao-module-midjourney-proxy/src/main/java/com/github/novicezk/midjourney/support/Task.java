package com.github.novicezk.midjourney.support;

import com.github.novicezk.midjourney.domain.DomainObject;
import com.github.novicezk.midjourney.enums.TaskAction;
import com.github.novicezk.midjourney.enums.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("任务")
public class Task extends DomainObject {
	@Serial
	private static final long serialVersionUID = -674915748204390789L;

	@ApiModelProperty("任务类型")
	private TaskAction action;
	@ApiModelProperty("任务状态")
	private TaskStatus status = TaskStatus.NOT_START;

	@ApiModelProperty("提示词")
	private String prompt;
	@ApiModelProperty("提示词-英文")
	private String promptEn;

	@ApiModelProperty("任务描述")
	private String description;
	@ApiModelProperty("自定义参数")
	private String state;

	@ApiModelProperty("提交时间")
	private Long submitTime;
	@ApiModelProperty("开始执行时间")
	private Long startTime;
	@ApiModelProperty("结束时间")
	private Long finishTime;

	@ApiModelProperty("图片url")
	private String imageUrl;

	@ApiModelProperty("任务进度")
	private String progress;
	@ApiModelProperty("失败原因")
	private String failReason;

	public void start() {
		this.startTime = System.currentTimeMillis();
		this.status = TaskStatus.SUBMITTED;
		this.progress = "0%";
	}

	public void success() {
		this.finishTime = System.currentTimeMillis();
		this.status = TaskStatus.SUCCESS;
		this.progress = "100%";
	}

	public void fail(String reason) {
		this.finishTime = System.currentTimeMillis();
		this.status = TaskStatus.FAILURE;
		this.failReason = reason;
		this.progress = "";
	}
}
