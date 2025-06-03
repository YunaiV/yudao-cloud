package com.github.novicezk.midjourney.wss.handle;

import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.Constants;
import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.loadbalancer.DiscordInstance;
import com.github.novicezk.midjourney.support.Task;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CaptchaEmbeddedHandler extends MessageHandler {

	@Override
	public int order() {
		return 2;
	}

	@Override
	public void handle(DiscordInstance instance, MessageType messageType, DataObject message) {
		String iframePath = message.getString("iframe_path", "");
		if (CharSequenceUtil.startWith(iframePath, "/captcha/embedded/")) {
			message.put(Constants.MJ_MESSAGE_HANDLED, true);
			String title = message.getString("title", "");
			String nonce = getMessageNonce(message);
			Task task = instance.getRunningTaskByNonce(nonce);
			String reason = "[" + title + "] " + iframePath;
			if (task != null) {
				task.fail(reason);
				task.awake();
			}
		}
	}

}