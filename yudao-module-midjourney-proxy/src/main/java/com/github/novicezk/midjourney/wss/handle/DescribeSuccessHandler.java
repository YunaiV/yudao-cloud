package com.github.novicezk.midjourney.wss.handle;

import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.enums.TaskAction;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.support.TaskCondition;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * describe消息处理.
 */
@Component
public class DescribeSuccessHandler extends MessageHandler {

	@Override
	public int order() {
		return 10;
	}

	@Override
	public void handle(DiscordInstance instance, MessageType messageType, DataObject message) {
		String messageId = message.getString("id");
		if (MessageType.CREATE.equals(messageType)) {
			String interactionName = getInteractionName(message);
			if (!"describe".equals(interactionName)) {
				return;
			}
			// 任务开始
			message.put(Constants.MJ_MESSAGE_HANDLED, true);
			String nonce = getMessageNonce(message);
			Task task = instance.getRunningTaskByNonce(nonce);
			if (task == null) {
				return;
			}
			task.setProperty(Constants.TASK_PROPERTY_PROGRESS_MESSAGE_ID, messageId);
		} else if (MessageType.UPDATE.equals(messageType)) {
			finishDescribeTask(instance, message, messageId);
		}
	}

	private void finishDescribeTask(DiscordInstance instance, DataObject message, String progressMessageId) {
		DataArray embeds = message.getArray("embeds");
		if (CharSequenceUtil.isBlank(progressMessageId) || embeds.isEmpty()) {
			return;
		}
		TaskCondition condition = new TaskCondition().setActionSet(Set.of(TaskAction.DESCRIBE)).setProgressMessageId(progressMessageId);
		Task task = instance.findRunningTask(condition).findFirst().orElse(null);
		if (task == null) {
			return;
		}
		message.put(Constants.MJ_MESSAGE_HANDLED, true);
		String description = embeds.getObject(0).getString("description");
		task.setPrompt(description);
		task.setPromptEn(description);
		task.setProperty(Constants.TASK_PROPERTY_FINAL_PROMPT, description);
		Optional<DataObject> imageOptional = embeds.getObject(0).optObject("image");
		if (imageOptional.isPresent()) {
			String imageUrl = imageOptional.get().getString("url");
			task.setImageUrl(replaceCdnUrl(imageUrl));
		}
		finishTask(task, message);
		task.awake();
	}

}
