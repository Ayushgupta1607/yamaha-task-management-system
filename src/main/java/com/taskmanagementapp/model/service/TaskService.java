package com.taskmanagementapp.model.service;

import java.util.List;

import com.taskmanagementapp.dto.UpdatetaskDTO;
import com.taskmanagementapp.model.persistance.Task;
import com.taskmanagementapp.model.persistance.User;

public interface TaskService {

	public Task createTask(String title, String description, User taskCreatedByUser,User taskAssignedToUser);

	public Task getTaskById(Integer task_id);

	public List<Task> getTasksCreatedByOrAssignedToUser(User user);
	
	public List<Task> getTasksCreatedBy(User user);
	public List<Task> getTasksAssignedToUser(User user);
	
	public Task updateTask(UpdatetaskDTO updateTaskDTO,String username);

	public void deleteTask(Integer task_id);
}
