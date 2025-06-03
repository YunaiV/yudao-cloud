package com.github.novicezk.midjourney.wss.handle;

import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.enums.TaskAction;
import com.github.novicezk.midjourney.enums.TaskStatus;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.support.TaskCondition;
import com.github.novicezk.midjourney.util.ContentParseData;
import com.github.novicezk.midjourney.util.ConvertUtils;
import lombok.Data;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * upscale消息处理.
 * 完成(create): **cat** - Upscaled (Beta\Light\Creative等) by <@1083152202048217169> (fast)
 * 完成(create): **cat** - Upscaled by <@1083152202048217169> (fast)
 * 完成(create): **cat** - Image #1 <@1012983546824114217>
 */
@Component
public class UpscaleSuccessHandler extends MessageHandler {
	private static final String CONTENT_REGEX_1 = "\\*\\*(.*)\\*\\* - Upscaled \\(.*?\\) by <@\\d+> \\((.*?)\\)";
	private static final String CONTENT_REGEX_2 = "\\*\\*(.*)\\*\\* - Upscaled by <@\\d+> \\((.*?)\\)";
	private static final String CONTENT_REGEX_U = "\\*\\*(.*)\\*\\* - Image #(\\d) <@\\d+>";

	@Override
	public void handle(DiscordInstance instance, MessageType messageType, DataObject message) {
		String content = getMessageContent(message);
		ContentParseData parseData = getParseData(content);
		if (MessageType.CREATE.equals(messageType) && parseData != null && hasImage(message)) {
			if (parseData instanceof UContentParseData uContentParseData) {
				findAndFinishUTask(instance, uContentParseData.getPrompt(), uContentParseData.getIndex(), message);
			} else {
				findAndFinishImageTask(instance, TaskAction.UPSCALE, parseData.getPrompt(), message);
			}
		}
	}

	private void findAndFinishUTask(DiscordInstance instance, String finalPrompt, int index, DataObject message) {
		String imageUrl = getImageUrl(message);
		String messageHash = this.discordHelper.getMessageHash(imageUrl);
		TaskCondition condition = new TaskCondition().setActionSet(Set.of(TaskAction.UPSCALE))
				.setFinalPrompt(finalPrompt)
				.setStatusSet(Set.of(TaskStatus.IN_PROGRESS));
		condition.setMessageHash(messageHash);
		Task task = instance.findRunningTask(condition).findFirst().orElse(null);
		if (task == null) {
			condition.setMessageHash(null);
			var predicate = condition.and(t -> CharSequenceUtil.endWith(t.getDescription(), "U" + index));
			String referencedMessageId = getReferencedMessageId(message);
			if (CharSequenceUtil.isNotBlank(referencedMessageId)) {
				predicate = predicate.and(t -> referencedMessageId.equals(t.getProperty(Constants.TASK_PROPERTY_REFERENCED_MESSAGE_ID, String.class)));
			}
			task = instance.findRunningTask(predicate).min(Comparator.comparing(Task::getStartTime)).orElse(null);
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

	private String getReferencedMessageId(DataObject message) {
		DataObject referencedMessage = message.optObject("referenced_message").orElse(DataObject.empty());
		return referencedMessage.getString("id", null);
	}

	public static ContentParseData getParseData(String content) {
		ContentParseData parseData = ConvertUtils.parseContent(content, CONTENT_REGEX_1);
		if (parseData == null) {
			parseData = ConvertUtils.parseContent(content, CONTENT_REGEX_2);
		}
		if (parseData != null) {
			return parseData;
		}
		Matcher matcher = Pattern.compile(CONTENT_REGEX_U).matcher(content);
		if (!matcher.find()) {
			return null;
		}
		UContentParseData uContentParseData = new UContentParseData();
		uContentParseData.setPrompt(matcher.group(1));
		uContentParseData.setIndex(Integer.parseInt(matcher.group(2)));
		uContentParseData.setStatus("done");
		return uContentParseData;
	}

	@Data
	public static class UContentParseData extends ContentParseData {
		private int index = 0;
	}

}
