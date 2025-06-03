package com.github.novicezk.midjourney.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@ApiModel("变化任务提交参数-simple")
@EqualsAndHashCode(callSuper = true)
public class SubmitSimpleChangeDTO extends BaseSubmitDTO {

	@ApiModelProperty(value = "变化描述: ID $action$index", required = true, example = "1320098173412546 U2")
	private String content;

}
