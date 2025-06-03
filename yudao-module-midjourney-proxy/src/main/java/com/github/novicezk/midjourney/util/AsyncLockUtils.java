package com.github.novicezk.midjourney.util;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.github.novicezk.midjourney.domain.DomainObject;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@UtilityClass
public class AsyncLockUtils {
	private static final TimedCache<String, LockObject> LOCK_MAP = CacheUtil.newTimedCache(Duration.ofDays(1).toMillis());
	private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

	public static LockObject getLock(String key) {
		synchronized (LOCK_MAP) {
			return LOCK_MAP.get(key);
		}
	}

	public static LockObject waitForLock(String key, Duration duration) throws TimeoutException {
		LockObject lockObject;
		synchronized (LOCK_MAP) {
			lockObject = LOCK_MAP.get(key);
			if (lockObject == null) {
				lockObject = new LockObject(key);
				LOCK_MAP.put(key, lockObject);
			}
		}
		final LockObject finalLockObject = lockObject;
		Future<?> future = EXECUTOR_SERVICE.submit(() -> {
			try {
				finalLockObject.sleep();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});
		try {
			future.get(duration.toMillis(), TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			log.trace(e.getMessage(), e);
		} catch (TimeoutException e) {
			future.cancel(true);
			throw new TimeoutException("Wait Timeout");
		} finally {
			synchronized (LOCK_MAP) {
				if (LOCK_MAP.get(key) == lockObject) {
					LOCK_MAP.remove(key);
				}
			}
		}
		return lockObject;
	}

	public static class LockObject extends DomainObject {

		public LockObject(String id) {
			this.id = id;
		}
	}
}
