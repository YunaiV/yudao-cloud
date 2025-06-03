package com.github.novicezk.midjourney.wss.handle;

import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.enums.TaskStatus;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.support.TaskCondition;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class ErrorMessageHandler extends MessageHandler {

	@Override
	public int order() {
		return 2;
	}

	@Override
	public void handle(DiscordInstance instance, MessageType messageType, DataObject message) {
		String content = getMessageContent(message);
		if (CharSequenceUtil.startWith(content, "Failed")) {
			// mj官方异常
			message.put(Constants.MJ_MESSAGE_HANDLED, true);
			String nonce = getMessageNonce(message);
			Task task = instance.getRunningTaskByNonce(nonce);
			if (task != null) {
				task.fail(content);
				task.awake();
			}
			return;
		}
		Optional<DataArray> embedsOptional = message.optArray("embeds");
		if (embedsOptional.isEmpty() || embedsOptional.get().isEmpty()) {
			return;
		}
		DataObject embed = embedsOptional.get().getObject(0);
		String title = embed.getString("title", null);
		if (CharSequenceUtil.isBlank(title)) {
			return;
		}
		String description = embed.getString("description", null);
		String footerText = "";
		Optional<DataObject> footer = embed.optObject("footer");
		if (footer.isPresent()) {
			footerText = footer.get().getString("text", "");
		}
		int color = embed.getInt("color", 0);
		if (color == 16239475) {
			log.warn("{} - MJ警告信息: {}\n{}\nfooter: {}", instance.getInstanceId(), title, description, footerText);
		} else if (color == 16711680) {
			message.put(Constants.MJ_MESSAGE_HANDLED, true);
			log.error("{} - MJ异常信息: {}\n{}\nfooter: {}", instance.getInstanceId(), title, description, footerText);
			String nonce = getMessageNonce(message);
			Task task;
			if (CharSequenceUtil.isNotBlank(nonce)) {
				task = instance.getRunningTaskByNonce(nonce);
			} else {
				task = findTaskWhenError(instance, messageType, message);
			}
			if (task != null) {
				task.fail("[" + title + "] " + description);
				task.awake();
			}
		} else {
			if ("link".equals(embed.getString("type", "")) || CharSequenceUtil.isBlank(description)) {
				return;
			}
			// 兼容 Invalid link! \ Could not complete 等错误
			Task task = findTaskWhenError(instance, messageType, message);
			if (task != null) {
				message.put(Constants.MJ_MESSAGE_HANDLED, true);
				log.warn("{} - MJ可能的异常信息: {}\n{}\nfooter: {}", instance.getInstanceId(), title, description, footerText);
				task.fail("[" + title + "] " + description);
				task.awake();
			}
		}
	}

	private Task findTaskWhenError(DiscordInstance instance, MessageType messageType, DataObject message) {
		String progressMessageId = null;
		if (MessageType.CREATE.equals(messageType)) {
			progressMessageId = getReferenceMessageId(message);
		} else if (MessageType.UPDATE.equals(messageType)) {
			progressMessageId = message.getString("id");
		}
		if (CharSequenceUtil.isBlank(progressMessageId)) {
			return null;
		}
		TaskCondition condition = new TaskCondition().setStatusSet(Set.of(TaskStatus.IN_PROGRESS, TaskStatus.SUBMITTED))
				.setProgressMessageId(progressMessageId);
		return instance.findRunningTask(condition).findFirst().orElse(null);
	}

}
