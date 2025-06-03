package com.github.novicezk.midjourney.dto;

import com.github.novicezk.midjourney.enums.BlendDimensions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel("Blend提交参数")
@EqualsAndHashCode(callSuper = true)
public class SubmitBlendDTO extends BaseSubmitDTO {

	@ApiModelProperty(value = "图片base64数组", required = true, example = "[\"data:image/png;base64,xxx1\", \"data:image/png;base64,xxx2\"]")
	private List<String> base64Array;

	@ApiModelProperty(value = "比例: PORTRAIT(2:3); SQUARE(1:1); LANDSCAPE(3:2)", example = "SQUARE")
	private BlendDimensions dimensions = BlendDimensions.SQUARE;
}
