package com.github.novicezk.midjourney.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {
	private static ApplicationContext APPLICATION_CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		APPLICATION_CONTEXT = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		if (APPLICATION_CONTEXT == null) {
			throw new IllegalStateException("SpringContextHolder is not ready.");
		}
		return APPLICATION_CONTEXT;
	}
}
