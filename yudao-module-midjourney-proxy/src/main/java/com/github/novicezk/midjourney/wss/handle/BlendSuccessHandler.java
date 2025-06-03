package com.github.novicezk.midjourney.wss.handle;


import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.enums.TaskAction;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.util.ContentParseData;
import com.github.novicezk.midjourney.util.ConvertUtils;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.springframework.stereotype.Component;

/**
 * blend消息处理.
 * 完成(create): **<https://s.mj.run/JWu6jaL1D-8> <https://s.mj.run/QhfnQY-l68o> --v 5.1** - <@1012983546824114217> (relaxed)
 */
@Component
public class BlendSuccessHandler extends MessageHandler {

	@Override
	public int order() {
		return 89;
	}

	@Override
	public void handle(DiscordInstance instance, MessageType messageType, DataObject message) {
		String content = getMessageContent(message);
		ContentParseData parseData = ConvertUtils.parseContent(content);
		if (parseData == null || !MessageType.CREATE.equals(messageType)) {
			return;
		}
		String interactionName = getInteractionName(message);
		if ("blend".equals(interactionName)) {
			// blend任务开始时，设置prompt
			Task task = instance.getRunningTaskByNonce(getMessageNonce(message));
			if (task != null) {
				task.setPromptEn(parseData.getPrompt());
				task.setPrompt(parseData.getPrompt());
			}
		}
		if (hasImage(message)) {
			findAndFinishImageTask(instance, TaskAction.BLEND, parseData.getPrompt(), message);
		}
	}
}
