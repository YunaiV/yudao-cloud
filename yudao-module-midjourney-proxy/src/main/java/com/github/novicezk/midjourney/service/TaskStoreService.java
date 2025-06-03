package com.github.novicezk.midjourney.service;


import com.github.novicezk.midjourney.support.Task;
import com.github.novicezk.midjourney.support.TaskCondition;

import java.util.List;

public interface TaskStoreService {

	void save(Task task);

	void delete(String id);

	Task get(String id);

	List<Task> list();

	List<Task> list(TaskCondition condition);

	Task findOne(TaskCondition condition);

}
