package com.github.novicezk.midjourney.result;

import com.github.novicezk.midjourney.ReturnCode;
import lombok.Getter;

@Getter
public class Message<T> {
	private final int code;
	private final String description;
	private final T result;

	public static <Y> Message<Y> success() {
		return new Message<>(ReturnCode.SUCCESS, "成功");
	}

	public static <T> Message<T> success(T result) {
		return new Message<>(ReturnCode.SUCCESS, "成功", result);
	}

	public static <T> Message<T> success(int code, String description, T result) {
		return new Message<>(code, description, result);
	}

	public static <Y> Message<Y> notFound() {
		return new Message<>(ReturnCode.NOT_FOUND, "数据未找到");
	}

	public static <Y> Message<Y> validationError() {
		return new Message<>(ReturnCode.VALIDATION_ERROR, "校验错误");
	}

	public static <Y> Message<Y> failure() {
		return new Message<>(ReturnCode.FAILURE, "系统异常");
	}

	public static <Y> Message<Y> failure(String description) {
		return new Message<>(ReturnCode.FAILURE, description);
	}

	public static <Y> Message<Y> of(int code, String description) {
		return new Message<>(code, description);
	}

	public static <T> Message<T> of(int code, String description, T result) {
		return new Message<>(code, description, result);
	}

	private Message(int code, String description) {
		this(code, description, null);
	}

	private Message(int code, String description, T result) {
		this.code = code;
		this.description = description;
		this.result = result;
	}
}
