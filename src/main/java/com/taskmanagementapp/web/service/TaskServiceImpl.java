package com.taskmanagementapp.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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
 *
 */

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
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
		User taskAssignedToUser = null;
		if (createTaskDto.getAssignedToId() != 0) {
			taskAssignedToUser = userRepository.findById(createTaskDto.getAssignedToId())
					.orElseThrow(() -> new UserNotFoundException("User not found"));
		}
		Task task = new Task(createTaskDto.getTitle(), createTaskDto.getDescription(), "TO-DO", currentUser,
				taskAssignedToUser);
		taskRepository.save(task);
		return null;
	}

	/**
	 * Method To Update a Task
	 * 
	 * @param updateTaskDto DTO for updating a task
	 * @param currentUser   User of application
	 * @return Task Updated Task
	 */
	public Task updateTask(UpdatetaskDto updateTaskDto, User currentUser) {
		
		Task task = taskRepository.findById(updateTaskDto.getAssignedToId())
				.orElseThrow(() -> new TaskNotFoundException("Task Not Found"));

		// Update description of Task
		task.setDescription(updateTaskDto.getDescription());

		// Update status of Task if status is true
		if (updateTaskDto.getStatus() != null && updateTaskDto.getStatus() == true) {
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
		taskRepository.save(task);

		// Add comments to Task if comments are provided
		if (updateTaskDto.getComments() != null) {
			for (String i : updateTaskDto.getComments()) {
				Comment comment = new Comment(i, currentUser, task);
				commentRepository.save(comment);
			}
		}

		return task;
	}

	/**
	 * Method to Delete a Task
	 * @param taskId Id of the task to be deleted
	 */
	@Override
	public void deleteTask(Integer task_id) {
		Task task = taskRepository.findById(task_id).orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
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
		List<Task> tasks = new ArrayList<Task>();
	
		if (filter == null)
			tasks = taskRepository.findtaskByCreatedByOrAssignedToAndIsDeleted(user, user, false);
		else if (filter.equals("createdByMe")) {
			tasks = taskRepository.findByCreatedByAndIsDeleted(user, false);
		} else if (filter.equals("assignedToMe"))
			tasks = taskRepository.findByAssignedToAndIsDeleted(user, false);
		return tasks;
	}

}
