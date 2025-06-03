package com.github.novicezk.midjourney.service;

import java.util.regex.Pattern;

public interface TranslateService {

	String translateToEnglish(String prompt);

	default boolean containsChinese(String prompt) {
		return Pattern.compile("[\u4e00-\u9fa5]").matcher(prompt).find();
	}

}
