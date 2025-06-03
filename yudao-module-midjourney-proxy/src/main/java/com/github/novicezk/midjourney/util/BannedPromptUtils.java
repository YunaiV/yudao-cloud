package com.github.novicezk.midjourney.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.github.novicezk.midjourney.exception.BannedPromptException;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class BannedPromptUtils {
	private static final String BANNED_WORDS_FILE_PATH = "/home/spring/config/banned-words.txt";
	private final List<String> BANNED_WORDS;

	static {
		List<String> lines;
		File file = new File(BANNED_WORDS_FILE_PATH);
		if (file.exists()) {
			lines = FileUtil.readLines(file, StandardCharsets.UTF_8);
		} else {
			var resource = BannedPromptUtils.class.getResource("/banned-words.txt");
			lines = FileUtil.readLines(resource, StandardCharsets.UTF_8);
		}
		BANNED_WORDS = lines.stream().filter(CharSequenceUtil::isNotBlank).toList();
	}

	public static void checkBanned(String promptEn) throws BannedPromptException {
		String finalPromptEn = promptEn.toLowerCase(Locale.ENGLISH);
		for (String word : BANNED_WORDS) {
			Matcher matcher = Pattern.compile("\\b" + word + "\\b").matcher(finalPromptEn);
			if (matcher.find()) {
				int index = CharSequenceUtil.indexOfIgnoreCase(promptEn, word);
				throw new BannedPromptException(promptEn.substring(index, index + word.length()));
			}
		}
	}

}
