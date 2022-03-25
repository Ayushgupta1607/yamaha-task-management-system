package com.taskmanagementapp.web.controller;

import java.security.Principal;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskmanagementapp.web.common.Constants;
import com.taskmanagementapp.web.common.Messages;
import com.taskmanagementapp.web.dto.CreateTaskDto;
import com.taskmanagementapp.web.dto.DeleteTaskDto;
import com.taskmanagementapp.web.dto.UpdatetaskDto;
import com.taskmanagementapp.web.model.entity.Task;
import com.taskmanagementapp.web.model.entity.User;
import com.taskmanagementapp.web.service.TaskService;
import com.taskmanagementapp.web.service.UserService;

/**
 * Task Controller Configuration
 * 
 * @author Ayush
 */
@RequestMapping(path = "/task")
@Controller
public class TaskController {
	private Logger logger = LoggerFactory.logger(TaskController.class);

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	/**
	 * Create a new Task
	 * 
	 * @param createTaskDto      Request DTO for creating new Task
	 * @param redirectAttributes Attributes to display messages
	 * @return redirect to home page
	 */
	@PostMapping(path = "/")
	public String createTask(ModelAndView mv, Principal principal, RedirectAttributes redirectAttributes,
			@ModelAttribute CreateTaskDto createTaskDto) {
		logger.info("Create Task request recieved");
		// Invoking User Service Method to retrieve current User
		String username = principal.getName();
		User currentUser = userService.getUserByUsername(username);
		// Invoking Task Service method to create Task
		taskService.createTask(createTaskDto, currentUser);

		redirectAttributes.addFlashAttribute(Constants.NOTIFICATION_HEADER, Messages.TASK_CREATED_SUCCESSFULLY);
		return Constants.REDIRECT_TASK;
	}

	/**
	 * Update an existing Task
	 * 
	 * @param updateTaskDto      Request DTO to update Task
	 * @param redirectAttributes Attributes to display message
	 * @return redirect to home page
	 */
	@PostMapping(path = "/updateTask")
	public String updateTask(Principal principal, RedirectAttributes redirectAttributes,
			@ModelAttribute UpdatetaskDto updateTaskDto) {
		logger.info("Update Task request recieved");
		// Invoking User Service Method to retrieve current User
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		// Invoking Task Service method to update Task
		taskService.updateTask(updateTaskDto, user);

		redirectAttributes.addFlashAttribute(Constants.NOTIFICATION_HEADER, Messages.TASK_UPDATED_SUCCESSFULLY);
		return Constants.REDIRECT_TASK;
	}

	/**
	 * Get the list of the tasks.
	 * 
	 * @param filter Request Parameter to filter tasks
	 * @return display home page
	 */
	@GetMapping(path = "")
	public ModelAndView viewTasks(Principal principal, ModelAndView mv, @Nullable @RequestParam String filter) {
		logger.info("View Tasks Page request recieved");

		mv.setViewName("taskhome");
		// Invoking User Service Method to retrieve current User
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		// Invoking Task Service Method to retrieve List of Tasks
		List<Task> tasks = taskService.getTasksOfUser(user, filter);
		// Invoking User Service Method to retrieve List of All Users
		List<User> users = userService.getAllUsers();
		// Providing Objects to use in view layer
		mv.addObject("tasks", tasks);
		mv.addObject("currentUser", user);
		mv.addObject("users", users);
		return mv;

	}

	/**
	 * Delete an existing Task
	 * 
	 * @param deleteTaskDto      Request DTO for deleting Task
	 * @param redirectAttributes Attributes to display messages
	 * @return redirect to home page
	 */
	@PostMapping(path = "/deleteTask")
	public String deleteTask(@ModelAttribute DeleteTaskDto deleteTaskDto, RedirectAttributes redirectAttributes) {
		logger.info("Delete Task request recieved");
		// Invoking Task Service method to delete Task
		taskService.deleteTask(deleteTaskDto.getTaskId());

		redirectAttributes.addFlashAttribute(Constants.NOTIFICATION_HEADER, Messages.TASK_DELETED_SUCCESSFULLY);
		return Constants.REDIRECT_TASK;
	}
}
