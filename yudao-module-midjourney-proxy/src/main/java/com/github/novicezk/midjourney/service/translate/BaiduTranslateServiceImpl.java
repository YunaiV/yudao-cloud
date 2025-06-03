package com.github.novicezk.midjourney.service.translate;


import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import com.github.novicezk.midjourney.ProxyProperties;
import com.github.novicezk.midjourney.service.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaiduTranslateServiceImpl implements TranslateService {
	private static final String TRANSLATE_API = "https://fanyi-api.baidu.com/api/trans/vip/translate";

	private final String appid;
	private final String appSecret;

	public BaiduTranslateServiceImpl(ProxyProperties.BaiduTranslateConfig translateConfig) {
		this.appid = translateConfig.getAppid();
		this.appSecret = translateConfig.getAppSecret();
		if (!CharSequenceUtil.isAllNotBlank(this.appid, this.appSecret)) {
			throw new BeanDefinitionValidationException("mj.baidu-translate.appid或mj.baidu-translate.app-secret未配置");
		}
	}

	@Override
	public String translateToEnglish(String prompt) {
		if (!containsChinese(prompt)) {
			return prompt;
		}
		String salt = RandomUtil.randomNumbers(5);
		String sign = MD5.create().digestHex(this.appid + prompt + salt + this.appSecret);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("from", "zh");
		body.add("to", "en");
		body.add("appid", this.appid);
		body.add("salt", salt);
		body.add("q", prompt);
		body.add("sign", sign);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
		try {
			ResponseEntity<String> responseEntity = new RestTemplate().exchange(TRANSLATE_API, HttpMethod.POST, requestEntity, String.class);
			if (responseEntity.getStatusCode() != HttpStatus.OK || CharSequenceUtil.isBlank(responseEntity.getBody())) {
				throw new ValidateException(responseEntity.getStatusCodeValue() + " - " + responseEntity.getBody());
			}
			JSONObject result = new JSONObject(responseEntity.getBody());
			if (result.has("error_code")) {
				throw new ValidateException(result.getString("error_code") + " - " + result.getString("error_msg"));
			}
			List<String> strings = new ArrayList<>();
			JSONArray transResult = result.getJSONArray("trans_result");
			for (int i = 0; i < transResult.length(); i++) {
				strings.add(transResult.getJSONObject(i).getString("dst"));
			}
			return CharSequenceUtil.join("\n", strings);
		} catch (Exception e) {
			log.warn("调用百度翻译失败: {}", e.getMessage());
		}
		return prompt;
	}

}
