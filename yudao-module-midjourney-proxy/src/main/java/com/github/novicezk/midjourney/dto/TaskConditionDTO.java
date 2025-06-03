package com.github.novicezk.midjourney.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("任务查询参数")
public class TaskConditionDTO {

	private List<String> ids;

}
