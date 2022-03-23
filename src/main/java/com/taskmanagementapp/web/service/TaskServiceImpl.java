package com.taskmanagementapp.web.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.model.entity.Comment;
import com.taskmanagementapp.model.entity.Task;
import com.taskmanagementapp.model.entity.User;
import com.taskmanagementapp.model.repository.CommentRepository;
import com.taskmanagementapp.model.repository.TaskRepository;
import com.taskmanagementapp.model.repository.UserRepository;
import com.taskmanagementapp.web.dto.CreateTaskDto;
import com.taskmanagementapp.web.dto.UpdatetaskDto;
import com.taskmanagementapp.web.exceptions.TaskNotFoundException;
import com.taskmanagementapp.web.exceptions.UserNotFoundException;

/**
 * Task Service Implementation
 * @author VE00YM351
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
	 * Function to create new Task
	*/	
	
	@Override
	public Task createTask(CreateTaskDto createTaskDto, User currentUser) {
		User taskAssignedToUser = null;
		if (createTaskDto.getAssigned_to_id() != 0) {
			taskAssignedToUser = userRepository.findById(createTaskDto.getAssigned_to_id()).orElseThrow(()->new UserNotFoundException("User not found"));
		}
		Task task=new Task(createTaskDto.getTitle(), createTaskDto.getDescription(),"TO-DO" ,currentUser,
				taskAssignedToUser);
		taskRepository.save(task);
		return null;
	}

	/**
	 * Function to update existing Task
	 */
	public Task updateTask(UpdatetaskDto updateTaskDto, String username) {
		User loggedInUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user not found"));
		Task task = this.getTaskById(updateTaskDto.getTask_id());

		// Condition to change description of Task
		if (updateTaskDto.getDescription() != null)
			task.setDescription(updateTaskDto.getDescription());

		// Condition to change status of Task
		if (updateTaskDto.getStatus() != null && updateTaskDto.getStatus() == true) {
			if (task.getStatus().equals("TO-DO")) {
				task.setStatus("IN-PROGRESS");
				task.setStartedDate(new Date());
			} else {
				task.setStatus("COMPLETED");
				task.setCompletionDate(new Date());
			}
		}

		// Condition to change AssignedTo User
		User taskAssignedToUser;
		if (updateTaskDto.getAssigned_to_id() != null) {
			if (updateTaskDto.getAssigned_to_id() == 0) {
				task.setAssignedTo(null);
			} else {
				taskAssignedToUser = userRepository.findById(updateTaskDto.getAssigned_to_id())
						.orElseThrow(() -> new UserNotFoundException("User not found"));
				task.setAssignedTo(taskAssignedToUser);
			}
		}
		taskRepository.save(task);

		// Conditions to add comments
		if (updateTaskDto.getComments() != null) {
			for (String i : updateTaskDto.getComments()) {
				Comment comment = new Comment(i, loggedInUser, task);
				commentRepository.save(comment);
			}
		}

		return null;
	}

	/**
	 * Function to get Task by Task ID;
	 */
	@Override
	public Task getTaskById(Integer task_id) {
		return taskRepository.findById(task_id).orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
	}

	/**
	 * Function to get List of all Tasks Created By or Assigned To a User
	 * @return List<Task>
	 */
	@Override
	public List<Task> getTasksCreatedByOrAssignedToUser(User user) {
		return taskRepository.findtaskByCreatedByOrAssignedToAndIsDeleted(user,user,false);
	}

	

	/**
	 * Function to get List of Tasks created by a User
	 * @return List<Task>
	 */
	@Override
	public List<Task> getTasksCreatedBy(User user) {
		return taskRepository.findByCreatedByAndIsDeleted(user,false);
		
	}

	/**
	 * Function to get List of Tasks assigned to a User
	 * @return List<Task>
	 */
	
	@Override
	public List<Task> getTasksAssignedToUser(User user) {
		System.out.println(taskRepository.findByCreatedByAndIsDeleted(user,false));
		return taskRepository.findByAssignedToAndIsDeleted(user,false);
	}

	/**
	 * Function to delete task
	 */
	@Override
	public void deleteTask(Integer task_id) {
		Task task=this.getTaskById(task_id);
		task.setDeleted(true);
		taskRepository.save(task);
	}
}
