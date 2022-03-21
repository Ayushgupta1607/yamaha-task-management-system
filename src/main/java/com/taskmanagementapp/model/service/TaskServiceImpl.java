package com.taskmanagementapp.model.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.dto.UpdatetaskDTO;
import com.taskmanagementapp.model.exceptions.TaskNotFoundException;
import com.taskmanagementapp.model.exceptions.UserNotFoundException;
import com.taskmanagementapp.model.persistance.Comment;
import com.taskmanagementapp.model.persistance.CommentRepository;
import com.taskmanagementapp.model.persistance.Task;
import com.taskmanagementapp.model.persistance.TaskRepository;
import com.taskmanagementapp.model.persistance.User;
import com.taskmanagementapp.model.persistance.UserRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Task createTask(String title, String description, User taskCreatedByUser, User taskAssignedToUser) {
		// TODO Auto-generated method stub
		System.out.println(taskAssignedToUser);
		Task task = new Task(title, description, "TO-DO", taskCreatedByUser, taskAssignedToUser);
		taskRepository.save(task);

		return task;
	}

	public Task updateTask(UpdatetaskDTO updateTaskDTO, String username) {
		User loggedInUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user not found"));
		Task task = taskRepository.findById(updateTaskDTO.getTask_id())
				.orElseThrow(() -> new TaskNotFoundException("task not found"));
		
		if (updateTaskDTO.getDescription() != null)
			task.setDescription(updateTaskDTO.getDescription());
		
		if (updateTaskDTO.getStatus()!=null) {
			System.out.println(task.getStatus());
			if (task.getStatus().equals("TO-DO")) {
				task.setStatus("IN-PROGRESS");
				task.setStartedDate(new Date());  
			} else {
				task.setStatus("COMPLETED");
				task.setCompletionDate(new Date());
			}
		}
		
		System.out.println(updateTaskDTO);
		User taskAssignedToUser = null;
		if (updateTaskDTO.getAssigned_to_id() != 0) {
			taskAssignedToUser = userRepository.findById(updateTaskDTO.getAssigned_to_id()).orElseThrow(()->new UserNotFoundException("User not found"));
		}
		task.setAssignedTo(taskAssignedToUser);
		taskRepository.save(task);
		
		
		if(updateTaskDTO.getComments()!="") {
//		for (String i : updateTaskDTO.getComments()) {
			Comment comment = new Comment(updateTaskDTO.getComments(),  loggedInUser,task);
			System.out.println(comment);
			commentRepository.save(comment); 
//		}}
		}
		return null;
	}

	@Override
	public Task getTaskById(Integer task_id) {
		return taskRepository.findById(task_id).orElseThrow(() -> new UserNotFoundException("mm"));
	}

	@Override
	public List<Task> getTasksCreatedByOrAssignedToUser(User user) {
		return taskRepository.findByCreatedByOrAssignedTo(user, user);
	}

	@Override
	public void deleteTask(Integer task_id) {
		taskRepository.deleteById(task_id);
	}

	@Override
	public List<Task> getTasksCreatedBy(User user) {
		// TODO Auto-generated method stub
		return taskRepository.findByCreatedBy(user);
	}

	@Override
	public List<Task> getTasksAssignedToUser(User user) {
		// TODO Auto-generated method stub
		return taskRepository.findByAssignedTo(user);
	}
}
