package com.github.novicezk.midjourney.wss.handle;


import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.enums.TaskStatus;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.support.TaskCondition;
import com.github.novicezk.midjourney.util.ContentParseData;
import com.github.novicezk.midjourney.util.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class StartAndProgressHandler extends MessageHandler {

	@Override
	public int order() {
		return 90;
	}

	@Override
	public void handle(DiscordInstance instance, MessageType messageType, DataObject message) {
		String nonce = getMessageNonce(message);
		String content = getMessageContent(message);
		ContentParseData parseData = ConvertUtils.parseContent(content);
		if (MessageType.CREATE.equals(messageType) && CharSequenceUtil.isNotBlank(nonce)) {
			// 任务开始
			Task task = instance.getRunningTaskByNonce(nonce);
			if (task == null) {
				return;
			}
			message.put(Constants.MJ_MESSAGE_HANDLED, true);
			task.setProperty(Constants.TASK_PROPERTY_PROGRESS_MESSAGE_ID, message.getString("id"));
			// 兼容少数content为空的场景
			if (parseData != null) {
				task.setProperty(Constants.TASK_PROPERTY_FINAL_PROMPT, parseData.getPrompt());
			}
			task.setStatus(TaskStatus.IN_PROGRESS);
			task.awake();
		} else if (MessageType.UPDATE.equals(messageType) && parseData != null) {
			// 任务进度
			if ("Stopped".equals(parseData.getStatus())) {
				return;
			}
			TaskCondition condition = new TaskCondition().setStatusSet(Set.of(TaskStatus.IN_PROGRESS, TaskStatus.SUBMITTED))
					.setProgressMessageId(message.getString("id"));
			Task task = instance.findRunningTask(condition).findFirst().orElse(null);
			if (task == null) {
				return;
			}
			message.put(Constants.MJ_MESSAGE_HANDLED, true);
			task.setProperty(Constants.TASK_PROPERTY_FINAL_PROMPT, parseData.getPrompt());
			task.setStatus(TaskStatus.IN_PROGRESS);
			task.setProgress(parseData.getStatus());
			String imageUrl = getImageUrl(message);
			task.setImageUrl(imageUrl);
			task.setProperty(Constants.TASK_PROPERTY_MESSAGE_HASH, this.discordHelper.getMessageHash(imageUrl));
			task.awake();
		}
	}

}
