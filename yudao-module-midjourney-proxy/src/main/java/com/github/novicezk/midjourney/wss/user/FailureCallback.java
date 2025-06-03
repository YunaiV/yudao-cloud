package com.github.novicezk.midjourney.wss.user;


public interface FailureCallback {
	void onFailure(int code, String reason);
}
