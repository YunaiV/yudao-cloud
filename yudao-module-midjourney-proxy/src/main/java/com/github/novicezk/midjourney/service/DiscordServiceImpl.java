package com.github.novicezk.midjourney.service;


import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.ReturnCode;
import com.github.novicezk.midjourney.domain.DiscordAccount;
import com.github.novicezk.midjourney.enums.BlendDimensions;
import com.github.novicezk.midjourney.result.Message;
import com.github.novicezk.midjourney.support.DiscordHelper;
import com.github.novicezk.midjourney.support.SpringContextHolder;
import eu.maxschuster.dataurl.DataUrl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
public class DiscordServiceImpl implements DiscordService {
	private static final String DEFAULT_SESSION_ID = "f1a313a09ce079ce252459dc70231f30";

	private final DiscordAccount account;
	private final Map<String, String> paramsMap;
	private final RestTemplate restTemplate;
	private final DiscordHelper discordHelper;

	private final String discordInteractionUrl;
	private final String discordAttachmentUrl;
	private final String discordMessageUrl;

	public DiscordServiceImpl(DiscordAccount account, RestTemplate restTemplate, Map<String, String> paramsMap) {
		this.account = account;
		this.restTemplate = restTemplate;
		this.discordHelper = SpringContextHolder.getApplicationContext().getBean(DiscordHelper.class);
		this.paramsMap = paramsMap;
		String discordServer = this.discordHelper.getServer();
		this.discordInteractionUrl = discordServer + "/api/v9/interactions";
		this.discordAttachmentUrl = discordServer + "/api/v9/channels/" + account.getChannelId() + "/attachments";
		this.discordMessageUrl = discordServer + "/api/v9/channels/" + account.getChannelId() + "/messages";
	}

	@Override
	public Message<Void> imagine(String prompt, String nonce) {
		String paramsStr = replaceInteractionParams(this.paramsMap.get("imagine"), nonce);
		JSONObject params = new JSONObject(paramsStr);
		params.getJSONObject("data").getJSONArray("options").getJSONObject(0)
				.put("value", prompt);
		return postJsonAndCheckStatus(params.toString());
	}

	@Override
	public Message<Void> upscale(String messageId, int index, String messageHash, int messageFlags, String nonce) {
		String paramsStr = replaceInteractionParams(this.paramsMap.get("upscale"), nonce)
				.replace("$message_id", messageId)
				.replace("$index", String.valueOf(index))
				.replace("$message_hash", messageHash);
		paramsStr = new JSONObject(paramsStr).put("message_flags", messageFlags).toString();
		return postJsonAndCheckStatus(paramsStr);
	}

	@Override
	public Message<Void> variation(String messageId, int index, String messageHash, int messageFlags, String nonce) {
		String paramsStr = replaceInteractionParams(this.paramsMap.get("variation"), nonce)
				.replace("$message_id", messageId)
				.replace("$index", String.valueOf(index))
				.replace("$message_hash", messageHash);
		paramsStr = new JSONObject(paramsStr).put("message_flags", messageFlags).toString();
		return postJsonAndCheckStatus(paramsStr);
	}

	@Override
	public Message<Void> reroll(String messageId, String messageHash, int messageFlags, String nonce) {
		String paramsStr = replaceInteractionParams(this.paramsMap.get("reroll"), nonce)
				.replace("$message_id", messageId)
				.replace("$message_hash", messageHash);
		paramsStr = new JSONObject(paramsStr).put("message_flags", messageFlags).toString();
		return postJsonAndCheckStatus(paramsStr);
	}

	@Override
	public Message<Void> describe(String finalFileName, String nonce) {
		String fileName = CharSequenceUtil.subAfter(finalFileName, "/", true);
		String paramsStr = replaceInteractionParams(this.paramsMap.get("describe"), nonce)
				.replace("$file_name", fileName)
				.replace("$final_file_name", finalFileName);
		return postJsonAndCheckStatus(paramsStr);
	}

	@Override
	public Message<Void> blend(List<String> finalFileNames, BlendDimensions dimensions, String nonce) {
		String paramsStr = replaceInteractionParams(this.paramsMap.get("blend"), nonce);
		JSONObject params = new JSONObject(paramsStr);
		JSONArray options = params.getJSONObject("data").getJSONArray("options");
		JSONArray attachments = params.getJSONObject("data").getJSONArray("attachments");
		for (int i = 0; i < finalFileNames.size(); i++) {
			String finalFileName = finalFileNames.get(i);
			String fileName = CharSequenceUtil.subAfter(finalFileName, "/", true);
			JSONObject attachment = new JSONObject().put("id", String.valueOf(i))
					.put("filename", fileName)
					.put("uploaded_filename", finalFileName);
			attachments.put(attachment);
			JSONObject option = new JSONObject().put("type", 11)
					.put("name", "image" + (i + 1))
					.put("value", i);
			options.put(option);
		}
		options.put(new JSONObject().put("type", 3)
				.put("name", "dimensions")
				.put("value", "--ar " + dimensions.getValue()));
		return postJsonAndCheckStatus(params.toString());
	}

	private String replaceInteractionParams(String paramsStr, String nonce) {
		return paramsStr.replace("$guild_id", this.account.getGuildId())
				.replace("$channel_id", this.account.getChannelId())
				.replace("$session_id", DEFAULT_SESSION_ID)
				.replace("$nonce", nonce);
	}

	@Override
	public Message<String> upload(String fileName, DataUrl dataUrl) {
		try {
			JSONObject fileObj = new JSONObject();
			fileObj.put("filename", fileName);
			fileObj.put("file_size", dataUrl.getData().length);
			fileObj.put("id", "0");
			JSONObject params = new JSONObject()
					.put("files", new JSONArray().put(fileObj));
			ResponseEntity<String> responseEntity = postJson(this.discordAttachmentUrl, params.toString());
			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				log.error("上传图片到discord失败, status: {}, msg: {}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
				return Message.of(ReturnCode.VALIDATION_ERROR, "上传图片到discord失败");
			}
			JSONArray array = new JSONObject(responseEntity.getBody()).getJSONArray("attachments");
			if (array.length() == 0) {
				return Message.of(ReturnCode.VALIDATION_ERROR, "上传图片到discord失败");
			}
			String uploadUrl = array.getJSONObject(0).getString("upload_url");
			String uploadFilename = array.getJSONObject(0).getString("upload_filename");
			putFile(uploadUrl, dataUrl);
			return Message.success(uploadFilename);
		} catch (Exception e) {
			log.error("上传图片到discord失败", e);
			return Message.of(ReturnCode.FAILURE, "上传图片到discord失败");
		}
	}

	@Override
	public Message<String> sendImageMessage(String content, String finalFileName) {
		String fileName = CharSequenceUtil.subAfter(finalFileName, "/", true);
		String paramsStr = this.paramsMap.get("message").replace("$content", content)
				.replace("$channel_id", this.account.getChannelId())
				.replace("$file_name", fileName)
				.replace("$final_file_name", finalFileName);
		ResponseEntity<String> responseEntity = postJson(this.discordMessageUrl, paramsStr);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			log.error("发送图片消息到discord失败, status: {}, msg: {}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
			return Message.of(ReturnCode.VALIDATION_ERROR, "发送图片消息到discord失败");
		}
		JSONObject result = new JSONObject(responseEntity.getBody());
		JSONArray attachments = result.optJSONArray("attachments");
		if (!attachments.isEmpty()) {
			return Message.success(attachments.getJSONObject(0).optString("url"));
		}
		return Message.failure("发送图片消息到discord失败: 图片不存在");
	}

	private void putFile(String uploadUrl, DataUrl dataUrl) {
		uploadUrl = this.discordHelper.getDiscordUploadUrl(uploadUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.add("User-Agent", this.account.getUserAgent());
		headers.setContentType(MediaType.valueOf(dataUrl.getMimeType()));
		headers.setContentLength(dataUrl.getData().length);
		HttpEntity<byte[]> requestEntity = new HttpEntity<>(dataUrl.getData(), headers);
		this.restTemplate.put(uploadUrl, requestEntity);
	}

	private ResponseEntity<String> postJson(String paramsStr) {
		return postJson(this.discordInteractionUrl, paramsStr);
	}

	private ResponseEntity<String> postJson(String url, String paramsStr) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", this.account.getUserToken());
		headers.set("User-Agent", this.account.getUserAgent());
		HttpEntity<String> httpEntity = new HttpEntity<>(paramsStr, headers);
		return this.restTemplate.postForEntity(url, httpEntity, String.class);
	}

	private Message<Void> postJsonAndCheckStatus(String paramsStr) {
		try {
			ResponseEntity<String> responseEntity = postJson(paramsStr);
			if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
				return Message.success();
			}
			return Message.of(responseEntity.getStatusCodeValue(), CharSequenceUtil.sub(responseEntity.getBody(), 0, 100));
		} catch (HttpStatusCodeException e) {
			return convertHttpStatusCodeException(e);
		}
	}

	private Message<Void> convertHttpStatusCodeException(HttpStatusCodeException e) {
		try {
			JSONObject error = new JSONObject(e.getResponseBodyAsString());
			return Message.of(error.optInt("code", e.getRawStatusCode()), error.optString("message"));
		} catch (Exception je) {
			return Message.of(e.getRawStatusCode(), CharSequenceUtil.sub(e.getMessage(), 0, 100));
		}
	}
}
