package com.github.novicezk.midjourney.support;

import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.enums.TaskAction;
import com.github.novicezk.midjourney.enums.TaskStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.function.Predicate;


@Data
@Accessors(chain = true)
public class TaskCondition implements Predicate<Task> {
	private String id;

	private Set<TaskStatus> statusSet;
	private Set<TaskAction> actionSet;

	// 精确匹配
	private String state;
	private String finalPrompt;
	private String messageId;
	private String messageHash;
	private String progressMessageId;
	private String nonce;
	private String instanceId;

	@Override
	public boolean test(Task task) {
		if (task == null) {
			return false;
		}
		if (CharSequenceUtil.isNotBlank(this.id) && !this.id.equals(task.getId())) {
			return false;
		}
		if (this.statusSet != null && !this.statusSet.isEmpty() && !this.statusSet.contains(task.getStatus())) {
			return false;
		}
		if (this.actionSet != null && !this.actionSet.isEmpty() && !this.actionSet.contains(task.getAction())) {
			return false;
		}
		if (CharSequenceUtil.isNotBlank(this.state) && !this.state.equals(task.getState())) {
			return false;
		}

		if (CharSequenceUtil.isNotBlank(this.finalPrompt) && !this.finalPrompt.equals(task.getProperty(Constants.TASK_PROPERTY_FINAL_PROMPT))) {
			return false;
		}
		if (CharSequenceUtil.isNotBlank(this.messageId) && !this.messageId.equals(task.getProperty(Constants.TASK_PROPERTY_MESSAGE_ID))) {
			return false;
		}
		if (CharSequenceUtil.isNotBlank(this.messageHash) && !this.messageHash.equals(task.getProperty(Constants.TASK_PROPERTY_MESSAGE_HASH))) {
			return false;
		}
		if (CharSequenceUtil.isNotBlank(this.progressMessageId) && !this.progressMessageId.equals(task.getProperty(Constants.TASK_PROPERTY_PROGRESS_MESSAGE_ID))) {
			return false;
		}
		if (CharSequenceUtil.isNotBlank(this.nonce) && !this.nonce.equals(task.getProperty(Constants.TASK_PROPERTY_NONCE))) {
			return false;
		}
		if (CharSequenceUtil.isNotBlank(this.instanceId) && !this.instanceId.equals(task.getProperty(Constants.TASK_PROPERTY_DISCORD_INSTANCE_ID))) {
			return false;
		}
		return true;
	}

}
