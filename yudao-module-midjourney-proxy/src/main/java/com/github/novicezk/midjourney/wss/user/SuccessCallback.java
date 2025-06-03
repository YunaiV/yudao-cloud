package com.github.novicezk.midjourney.wss.user;


public interface SuccessCallback {

	void onSuccess(String sessionId, Object sequence, String resumeGatewayUrl);
}
