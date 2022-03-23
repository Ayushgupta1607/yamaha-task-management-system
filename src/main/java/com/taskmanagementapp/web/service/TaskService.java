package com.taskmanagementapp.web.service;

import java.util.List;

import com.taskmanagementapp.web.dto.CreateTaskDto;
import com.taskmanagementapp.web.dto.UpdatetaskDto;
import com.taskmanagementapp.web.model.entity.Task;
import com.taskmanagementapp.web.model.entity.User;

/**
 * Task Service Interface
 * 
 * @author Ayush
 * 
 */
public interface TaskService {
	/**
	 * Method To Create a new Task
	 * 
	 * @param createTaskDto DTO for creating new task
	 * @param currentUser   Current User of application
	 * @return Task created task
	 */
	public Task createTask(CreateTaskDto createTaskDto, User currentUser);

	/**
	 * Method to Retrieve List of Tasks of a User
	 * 
	 * @param user   User of application
	 * @param filter Filter for List of Tasks
	 * @return List<Task> List of Tasks 
	 */
	public List<Task> getTasksOfUser(User user, String filter);

	/**
	 * Method To Update a Task
	 * 
	 * @param updateTaskDto DTO for updating a task
	 * @param currentUser   User of application
	 * @return Task Updated Task
	 */
	public Task updateTask(UpdatetaskDto updateTaskDto, User currentUser);

	/**
	 * Method to Delete a Task
	 * @param taskId Id of the task to be deleted
	 */
	public void deleteTask(Integer taskId);
}
