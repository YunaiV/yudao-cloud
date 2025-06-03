package com.github.novicezk.midjourney.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@ApiModel("Imagine提交参数")
@EqualsAndHashCode(callSuper = true)
public class SubmitImagineDTO extends BaseSubmitDTO {

	@ApiModelProperty(value = "提示词", required = true, example = "Cat")
	private String prompt;

	@ApiModelProperty(value = "垫图base64数组")
	private List<String> base64Array;

	@ApiModelProperty(hidden = true)
	@Deprecated(since = "3.0", forRemoval = true)
	private String base64;

}
