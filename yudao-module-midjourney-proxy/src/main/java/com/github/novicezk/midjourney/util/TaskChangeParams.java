package com.github.novicezk.midjourney.util;

import com.github.novicezk.midjourney.enums.TaskAction;
import lombok.Data;

@Data
public class TaskChangeParams {
	private String id;
	private TaskAction action;
	private Integer index;
}
