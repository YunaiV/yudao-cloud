package com.github.novicezk.midjourney.service.store;

import com.github.novicezk.midjourney.service.TaskStoreService;
import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.support.TaskCondition;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RedisTaskStoreServiceImpl implements TaskStoreService {
	private static final String KEY_PREFIX = "mj-task-store::";

	private final Duration timeout;
	private final RedisTemplate<String, Task> redisTemplate;

	public RedisTaskStoreServiceImpl(Duration timeout, RedisTemplate<String, Task> redisTemplate) {
		this.timeout = timeout;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void save(Task task) {
		this.redisTemplate.opsForValue().set(getRedisKey(task.getId()), task, this.timeout);
	}

	@Override
	public void delete(String id) {
		this.redisTemplate.delete(getRedisKey(id));
	}

	@Override
	public Task get(String id) {
		return this.redisTemplate.opsForValue().get(getRedisKey(id));
	}

	@Override
	public List<Task> list() {
		Set<String> keys = this.redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
			Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(KEY_PREFIX + "*").count(1000).build());
			return cursor.stream().map(String::new).collect(Collectors.toSet());
		});
		if (keys == null || keys.isEmpty()) {
			return Collections.emptyList();
		}
		ValueOperations<String, Task> operations = this.redisTemplate.opsForValue();
		return keys.stream().map(operations::get)
				.filter(Objects::nonNull)
				.toList();
	}

	@Override
	public List<Task> list(TaskCondition condition) {
		return list().stream().filter(condition).toList();
	}

	@Override
	public Task findOne(TaskCondition condition) {
		return list().stream().filter(condition).findFirst().orElse(null);
	}

	private String getRedisKey(String id) {
		return KEY_PREFIX + id;
	}

}
