package com.taskmanagementapp.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

import com.taskmanagementapp.model.entity.Task;
import com.taskmanagementapp.model.entity.User;
import com.taskmanagementapp.web.dto.CreateTaskDto;
import com.taskmanagementapp.web.dto.DeleteTaskDto;
import com.taskmanagementapp.web.dto.UpdatetaskDto;
import com.taskmanagementapp.web.service.TaskService;
import com.taskmanagementapp.web.service.UserService;

/**
 * Task Controller Configuration
 */

@RequestMapping(path = "/task")
@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	/**
	 * Create a new Task
	 * @param createTaskDto Request DTO for creating new Task
	 * @param redirectAttributes Attributes to display messages
	 * @return redirect to home page
	 */
	@PostMapping(path = "/")
	public String createTask(ModelAndView mv, Principal principal, RedirectAttributes redirectAttributes,
			@ModelAttribute CreateTaskDto createTaskDto) {
		String username = principal.getName();
		User currentUser = userService.getUserByUsername(username);
		taskService.createTask(createTaskDto, currentUser);

		redirectAttributes.addFlashAttribute("message", "Task created successfully");
		return "redirect:/task/";
	}

	/**
	 * Update an existing Task
	 * @param updateTaskDto Request DTO to update Task
	 * @param redirectAttributes Attributes to display message 
	 * @return redirect to home page
	 */
	@PostMapping(path = "/updateTask")
	public String updateTask(Principal principal, RedirectAttributes redirectAttributes,
			@ModelAttribute UpdatetaskDto updateTaskDto) {
		String username = principal.getName();
		taskService.updateTask(updateTaskDto, username);
		redirectAttributes.addFlashAttribute("message", "Task Updated Successfully");

		return "redirect:/task/";
	}

	/**
	 * Get the list of the tasks.
	 * @param filter Request Parameter to filter tasks
	 * @return display home page
	 */
	@GetMapping(path = "")
	public ModelAndView viewTasks(Principal principal, ModelAndView mv, @Nullable @RequestParam String filter) {
		mv.setViewName("taskhome");
		String username = principal.getName();
		User user = userService.getUserByUsername(username);

		List<Task> tasks = new ArrayList<Task>();

		if (filter == null)
			tasks = taskService.getTasksCreatedByOrAssignedToUser(user);
		else if (filter.equals("createdByMe")) {
			tasks = taskService.getTasksCreatedBy(user);
		} else if (filter.equals("assignedToMe"))
			tasks = taskService.getTasksAssignedToUser(user);

		List<User> users = userService.getAllUsers();

		mv.addObject("tasks", tasks);
		mv.addObject("currentUser", user);
		mv.addObject("users", users);
		return mv;

	}

	/**
	 * Delete an existing Task
	 * @param deleteTaskDto Request DTO for deleting Task
	 * @param redirectAttributes Attributes to display messages
	 * @return redirect to home page
	 */
	
	@PostMapping(path = "/deleteTask")
	public String deleteTask(@ModelAttribute DeleteTaskDto deleteTaskDto, RedirectAttributes redirectAttributes) {
		taskService.deleteTask(deleteTaskDto.getTask_id());
		redirectAttributes.addFlashAttribute("message", "Task Deleted Successfully");

		return "redirect:/task/";
	}
}
