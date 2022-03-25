package com.taskmanagementapp.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.web.dto.CreateTaskDto;
import com.taskmanagementapp.web.dto.UpdatetaskDto;
import com.taskmanagementapp.web.exceptions.TaskNotFoundException;
import com.taskmanagementapp.web.exceptions.UserNotFoundException;
import com.taskmanagementapp.web.model.entity.Comment;
import com.taskmanagementapp.web.model.entity.Task;
import com.taskmanagementapp.web.model.entity.User;
import com.taskmanagementapp.web.repository.CommentRepository;
import com.taskmanagementapp.web.repository.TaskRepository;
import com.taskmanagementapp.web.repository.UserRepository;

/**
 * Task Service Implementation
 * 
 * @author Ayush
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	private Logger logger = LoggerFactory.logger(TaskServiceImpl.class);

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * Method To Create a new Task
	 * 
	 * @param createTaskDto DTO for creating new task
	 * @param currentUser   Current User of application
	 * @return Task created task
	 */
	@Override
	public Task createTask(CreateTaskDto createTaskDto, User currentUser) {
		logger.debug("Create Task Method of Task Service is called with Parameters createTaskDto:" + createTaskDto
				+ "currentUser" + currentUser);
		// Invoke user repository to find assigned to user
		User taskAssignedToUser = null;
		if (createTaskDto.getAssignedToId() != 0) {
			taskAssignedToUser = userRepository.findById(createTaskDto.getAssignedToId())
					.orElseThrow(() -> new UserNotFoundException("User not found"));
		}
		// Invoke task repository to create new task
		Task task = new Task(createTaskDto.getTitle(), createTaskDto.getDescription(), "TO-DO", currentUser,
				taskAssignedToUser);
		taskRepository.save(task);
		logger.debug("Task created Successfully, task:" + task);
		return task;
	}

	/**
	 * Method To Update a Task
	 * 
	 * @param updateTaskDto DTO for updating a task
	 * @param currentUser   User of application
	 * @return Task Updated Task
	 */
	public Task updateTask(UpdatetaskDto updateTaskDto, User currentUser) {
		logger.debug("Update Task Method of Task Service is called with Parameters updateTaskDto:" + updateTaskDto
				+ " currentUser:" + currentUser);
		// Invoke task repository to find task
		Task task = taskRepository.findById(updateTaskDto.getTaskId())
				.orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
		// Update description of Task
		task.setDescription(updateTaskDto.getDescription());
		// Update status of Task if status is true
		if (Boolean.TRUE.equals(updateTaskDto.getStatus())) {
			if (task.getStatus().equals("TO-DO")) {
				task.setStatus("IN-PROGRESS");
				task.setStartedDate(new Date());
			} else {
				task.setStatus("COMPLETED");
				task.setCompletionDate(new Date());
			}
		}
		// Update assignedTo of Task if AssignedToId is provided
		User taskAssignedToUser;
		if (updateTaskDto.getAssignedToId() != null) {
			if (updateTaskDto.getAssignedToId() == 0) {
				task.setAssignedTo(null);
			} else {
				taskAssignedToUser = userRepository.findById(updateTaskDto.getAssignedToId())
						.orElseThrow(() -> new UserNotFoundException("User not found"));
				task.setAssignedTo(taskAssignedToUser);
			}
		}
		// Invoke task repository to update Task
		taskRepository.save(task);
		// Add comments to Task if comments are provided
		if (updateTaskDto.getComments() != null) {
			for (String i : updateTaskDto.getComments()) {
				Comment comment = new Comment(i, currentUser, task);
				commentRepository.save(comment);
			}
		}
		logger.debug("Task Updated Successfully,task:" + task);
		return task;
	}

	/**
	 * Method to Delete a Task
	 * 
	 * @param taskId Id of the task to be deleted
	 */
	@Override
	public void deleteTask(Integer taskId) {
		logger.debug("Delete Task Method of Task Service is called with Parameters taskId:" + taskId);
		// Invoke Database to find and delete task
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
		task.setDeleted(true);
		taskRepository.save(task);
	}

	/**
	 * Method to Retrieve List of Tasks of a User
	 * 
	 * @param user   User of application
	 * @param filter Filter for List of Tasks
	 * @return List<Task> List of Tasks
	 */
	@Override
	public List<Task> getTasksOfUser(User user, String filter) {
		logger.debug("Get Task Method of Task Service is called with Parameters user:" + user + " filter:" + filter);
		List<Task> tasks = new ArrayList<>();
		// Invoke Database to find tasks of user
		if (filter == null)
			tasks = taskRepository.findTaskCreatedByOrAssignedToAndIsDeleted(user, user, false);
		else if (filter.equals("createdByMe")) {
			tasks = taskRepository.findByCreatedByAndIsDeleted(user, false);
		} else if (filter.equals("assignedToMe"))
			tasks = taskRepository.findByAssignedToAndIsDeleted(user, false);
		logger.debug("Tasks retrieved:" + tasks);
		return tasks;
	}

}
