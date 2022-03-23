package com.taskmanagementapp.web.service;

import java.util.List;

import com.taskmanagementapp.model.entity.Task;
import com.taskmanagementapp.model.entity.User;
import com.taskmanagementapp.web.dto.CreateTaskDto;
import com.taskmanagementapp.web.dto.UpdatetaskDto;

public interface TaskService {

	public Task createTask(CreateTaskDto createTaskDto,User currentUser);

	public Task getTaskById(Integer task_id);

	public List<Task> getTasksCreatedByOrAssignedToUser(User user);
	
	public List<Task> getTasksCreatedBy(User user);
	public List<Task> getTasksAssignedToUser(User user);
	
	public Task updateTask(UpdatetaskDto updateTaskDto,String username);

	public void deleteTask(Integer task_id);
}
