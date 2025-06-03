package com.github.novicezk.midjourney.wss.handle;

import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.enums.TaskAction;
import com.github.novicezk.midjourney.enums.TaskStatus;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.loadbalancer.DiscordLoadBalancer;
import com.github.novicezk.midjourney.support.DiscordHelper;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.support.TaskCondition;
import com.github.novicezk.midjourney.util.ConvertUtils;
import jakarta.annotation.Resource;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

public abstract class MessageHandler {
	@Resource
	protected DiscordLoadBalancer discordLoadBalancer;
	@Resource
	protected DiscordHelper discordHelper;

	public abstract void handle(DiscordInstance instance, MessageType messageType, DataObject message);

	public int order() {
		return 100;
	}

	protected String getMessageContent(DataObject message) {
		return message.hasKey("content") ? message.getString("content") : "";
	}

	protected String getMessageNonce(DataObject message) {
		return message.hasKey("nonce") ? message.getString("nonce") : "";
	}

	protected String getInteractionName(DataObject message) {
		Optional<DataObject> interaction = message.optObject("interaction");
		return interaction.map(dataObject -> dataObject.getString("name", "")).orElse("");
	}

	protected String getReferenceMessageId(DataObject message) {
		Optional<DataObject> reference = message.optObject("message_reference");
		return reference.map(dataObject -> dataObject.getString("message_id", "")).orElse("");
	}

	protected void findAndFinishImageTask(DiscordInstance instance, TaskAction action, String finalPrompt, DataObject message) {
		if (CharSequenceUtil.isBlank(finalPrompt)) {
			return;
		}
		String imageUrl = getImageUrl(message);
		String messageHash = this.discordHelper.getMessageHash(imageUrl);
		TaskCondition condition = new TaskCondition().setActionSet(Set.of(action)).setFinalPrompt(finalPrompt).setStatusSet(Set.of(TaskStatus.IN_PROGRESS));
		condition.setMessageHash(messageHash);
		Task task = instance.findRunningTask(condition).findFirst().orElse(null);
		if (task == null) {
			condition.setMessageHash(null);
			task = instance.findRunningTask(condition).min(Comparator.comparing(Task::getStartTime)).orElse(null);
		}
		if (task == null && !TaskAction.BLEND.equals(action)) {
			condition.setFinalPrompt(null);
			condition.setStatusSet(Set.of(TaskStatus.SUBMITTED));
			String matchPrompt = ConvertUtils.getPrimaryPrompt(finalPrompt).replaceAll("\\s+", "");
			task = instance.findRunningTask(condition).filter(t -> matchPrompt.equals(ConvertUtils.getPrimaryPrompt(t.getPromptEn()).replaceAll("\\s+", "")))
					.min(Comparator.comparing(Task::getStartTime)).orElse(null);
		}
		if (task == null) {
			return;
		}
		message.put(Constants.MJ_MESSAGE_HANDLED, true);
		task.setProperty(Constants.TASK_PROPERTY_FINAL_PROMPT, finalPrompt);
		task.setProperty(Constants.TASK_PROPERTY_MESSAGE_HASH, messageHash);
		task.setImageUrl(imageUrl);
		finishTask(task, message);
		task.awake();
	}

	protected void finishTask(Task task, DataObject message) {
		task.setProperty(Constants.TASK_PROPERTY_MESSAGE_ID, message.getString("id"));
		task.setProperty(Constants.TASK_PROPERTY_FLAGS, message.getInt("flags", 0));
		task.setProperty(Constants.TASK_PROPERTY_MESSAGE_HASH, this.discordHelper.getMessageHash(task.getImageUrl()));
		task.success();
	}

	protected boolean hasImage(DataObject message) {
		DataArray attachments = message.optArray("attachments").orElse(DataArray.empty());
		return !attachments.isEmpty();
	}

	protected String getImageUrl(DataObject message) {
		DataArray attachments = message.getArray("attachments");
		if (!attachments.isEmpty()) {
			String imageUrl = attachments.getObject(0).getString("url");
			return replaceCdnUrl(imageUrl);
		}
		return null;
	}

	protected String replaceCdnUrl(String imageUrl) {
		if (CharSequenceUtil.isBlank(imageUrl)) {
			return imageUrl;
		}
		String cdn = this.discordHelper.getCdn();
		if (CharSequenceUtil.startWith(imageUrl, cdn)) {
			return imageUrl;
		}
		return CharSequenceUtil.replaceFirst(imageUrl, DiscordHelper.DISCORD_CDN_URL, cdn);
	}

}
