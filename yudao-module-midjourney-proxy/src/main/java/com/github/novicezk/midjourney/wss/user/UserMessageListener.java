package com.github.novicezk.midjourney.wss.user;


import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.wss.handle.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.utils.data.DataObject;

import java.util.List;

@Slf4j
public class UserMessageListener {
	private DiscordInstance instance;
	private final List<MessageHandler> messageHandlers;

	public UserMessageListener(List<MessageHandler> messageHandlers) {
		this.messageHandlers = messageHandlers;
	}

	public void setInstance(DiscordInstance instance) {
		this.instance = instance;
	}

	public void onMessage(DataObject raw) {
		MessageType messageType = MessageType.of(raw.getString("t"));
		if (messageType == null || MessageType.DELETE == messageType) {
			return;
		}
		DataObject data = raw.getObject("d");
		if (ignoreAndLogMessage(data, messageType)) {
			return;
		}
		ThreadUtil.sleep(50);
		for (MessageHandler messageHandler : this.messageHandlers) {
			if (data.getBoolean(Constants.MJ_MESSAGE_HANDLED, false)) {
				return;
			}
			messageHandler.handle(this.instance, messageType, data);
		}
	}

	private boolean ignoreAndLogMessage(DataObject data, MessageType messageType) {
		String channelId = data.getString("channel_id");
		if (!CharSequenceUtil.equals(channelId, this.instance.account().getChannelId())) {
			return true;
		}
		String authorName = data.optObject("author").map(a -> a.getString("username")).orElse("System");
		log.debug("{} - {} - {}: {}", this.instance.account().getDisplay(), messageType.name(), authorName, data.opt("content").orElse(""));
		return false;
	}
}
