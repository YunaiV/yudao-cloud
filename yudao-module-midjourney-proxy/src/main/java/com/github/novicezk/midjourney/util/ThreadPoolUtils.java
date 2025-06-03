package com.github.novicezk.midjourney.util;


import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public final class ThreadPoolUtils {
	private ThreadPoolUtils() {
	}

	public static ThreadPoolExecutor newFixedThreadPool(String prefix, int coreSize) {
		var threadFactory = ThreadUtil.createThreadFactory(prefix);
		return ExecutorBuilder.create().setThreadFactory(threadFactory)
				.setCorePoolSize(coreSize)
				.setWorkQueue(new LinkedBlockingQueue<>())
				.build();
	}

	public static ThreadPoolExecutor newSynchronousQueueThreadPoolExecutor(String prefix) {
		var threadFactory = ThreadUtil.createThreadFactory(prefix);
		return ExecutorBuilder.create().setCorePoolSize(0)
				.setThreadFactory(threadFactory)
				.useSynchronousQueue()
				.build();
	}

	public static ThreadPoolExecutor newThreadPoolExecutor(String prefix, int coreSize, int maxSize, int queueCapacity) {
		var threadFactory = ThreadUtil.createThreadFactory(prefix);
		return ExecutorBuilder.create().setThreadFactory(threadFactory)
				.setCorePoolSize(coreSize)
				.setMaxPoolSize(maxSize)
				.setWorkQueue(new ArrayBlockingQueue<>(queueCapacity))
				.build();
	}

}
