package com.github.novicezk.midjourney.service.translate;


import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.ProxyProperties;
import com.github.novicezk.midjourney.service.TranslateService;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatChoice;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import com.unfbx.chatgpt.interceptor.OpenAiResponseInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GPTTranslateServiceImpl implements TranslateService {
	private final OpenAiClient openAiClient;
	private final ProxyProperties.OpenaiConfig openaiConfig;

	public GPTTranslateServiceImpl(ProxyProperties properties) {
		this.openaiConfig = properties.getOpenai();
		if (CharSequenceUtil.isBlank(this.openaiConfig.getGptApiKey())) {
			throw new BeanDefinitionValidationException("mj.openai.gpt-api-key未配置");
		}
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
		OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
				.addInterceptor(httpLoggingInterceptor)
				.addInterceptor(new OpenAiResponseInterceptor())
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(30, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS);
		if (CharSequenceUtil.isNotBlank(properties.getProxy().getHost())) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(properties.getProxy().getHost(), properties.getProxy().getPort()));
			okHttpBuilder.proxy(proxy);
		}
		OpenAiClient.Builder apiBuilder = OpenAiClient.builder()
				.apiKey(Collections.singletonList(this.openaiConfig.getGptApiKey()))
				.keyStrategy(new KeyRandomStrategy())
				.okHttpClient(okHttpBuilder.build());
		if (CharSequenceUtil.isNotBlank(this.openaiConfig.getGptApiUrl())) {
			apiBuilder.apiHost(this.openaiConfig.getGptApiUrl());
		}
		this.openAiClient = apiBuilder.build();
	}

	@Override
	public String translateToEnglish(String prompt) {
		if (!containsChinese(prompt)) {
			return prompt;
		}
		Message m1 = Message.builder().role(Message.Role.SYSTEM).content("把中文翻译成英文").build();
		Message m2 = Message.builder().role(Message.Role.USER).content(prompt).build();
		ChatCompletion chatCompletion = ChatCompletion.builder()
				.messages(Arrays.asList(m1, m2))
				.model(this.openaiConfig.getModel())
				.temperature(this.openaiConfig.getTemperature())
				.maxTokens(this.openaiConfig.getMaxTokens())
				.build();
		ChatCompletionResponse chatCompletionResponse = this.openAiClient.chatCompletion(chatCompletion);
		try {
			List<ChatChoice> choices = chatCompletionResponse.getChoices();
			if (!choices.isEmpty()) {
				return choices.get(0).getMessage().getContent();
			}
		} catch (Exception e) {
			log.warn("调用chat-gpt接口翻译中文失败: {}", e.getMessage());
		}
		return prompt;
	}
}