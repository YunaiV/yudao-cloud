package com.github.novicezk.midjourney.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class MimeTypeUtils {
	private final Map<String, List<String>> MIME_TYPE_MAP;

	static {
		MIME_TYPE_MAP = new HashMap<>();
		var resource = MimeTypeUtils.class.getResource("/mime.types");
		var lines = FileUtil.readLines(resource, StandardCharsets.UTF_8);
		for (var line : lines) {
			if (CharSequenceUtil.isBlank(line)) {
				continue;
			}
			var arr = line.split(":");
			MIME_TYPE_MAP.put(arr[0], CharSequenceUtil.split(arr[1], ' '));
		}
	}

	public static String guessFileSuffix(String mimeType) {
		if (CharSequenceUtil.isBlank(mimeType)) {
			return null;
		}
		String key = mimeType;
		if (!MIME_TYPE_MAP.containsKey(key)) {
			key = MIME_TYPE_MAP.keySet().stream().filter(k -> CharSequenceUtil.startWithIgnoreCase(mimeType, k))
					.findFirst().orElse(null);
		}
		var suffixList = MIME_TYPE_MAP.get(key);
		if (suffixList == null || suffixList.isEmpty()) {
			return null;
		}
		return suffixList.iterator().next();
	}

}
