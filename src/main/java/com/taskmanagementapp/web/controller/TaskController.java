package com.taskmanagementapp.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskmanagementapp.dto.CreateTaskDTO;
import com.taskmanagementapp.dto.DeleteTaskDTO;
import com.taskmanagementapp.dto.UpdatetaskDTO;
import com.taskmanagementapp.model.persistance.Task;
import com.taskmanagementapp.model.persistance.User;
import com.taskmanagementapp.model.service.TaskService;
import com.taskmanagementapp.model.service.UserService;

@RequestMapping(path = "/task")
@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	// this route will be used to create a task
	// It will take title,description,assigned_to_id as parameters
	@PostMapping(path = "/")
	public String createTask(ModelAndView mv,Principal principal,RedirectAttributes redirectAttributes, @ModelAttribute CreateTaskDTO createTaskDTO) {
		System.out.println(createTaskDTO);
		String username = principal.getName();
		System.out.println(createTaskDTO);
		User taskCreatedByUser = userService.getUserByUsername(username);
		User taskAssignedToUser = null;
		if (createTaskDTO.getAssigned_to_id() != 0) {
			taskAssignedToUser = userService.getUserById(createTaskDTO.getAssigned_to_id());
		}
		taskService.createTask(createTaskDTO.getTitle(), createTaskDTO.getDescription(), taskCreatedByUser,
				taskAssignedToUser);

		redirectAttributes.addFlashAttribute("message","task-created");
		return "redirect:/task/";
	}

	// this route will be used to update a task
	// It will take task_id, description ,status ,assigned_to_id and comments as
	// parameters
	@PostMapping(path = "/updateTask")
	public String updateTask(Principal principal,RedirectAttributes redirectAttributes, @ModelAttribute UpdatetaskDTO updateTaskDTO) {
		System.out.println(updateTaskDTO);
		String username = principal.getName();
		taskService.updateTask(updateTaskDTO, username);
		redirectAttributes.addFlashAttribute("message","task-updated");

		return "redirect:/task/";
	}

	// This route will act as homepage for the application and will show all the
	// tasks create by or assigned to the user according to the filter
	@GetMapping(path = "")
	public ModelAndView viewTasks(Principal principal, ModelAndView mv, @Nullable @RequestParam String filter) {
		mv.setViewName("home");
		String username = principal.getName();
		User user = userService.getUserByUsername(username);

		List<Task> tasks = new ArrayList<Task>();

		if (filter == null)
			tasks = taskService.getTasksCreatedByOrAssignedToUser(user);
		else if (filter.equals("createdByMe")) {
			System.out.println(1);
			tasks = taskService.getTasksCreatedBy(user);
		} else if (filter.equals("assignedToMe"))
			tasks = taskService.getTasksAssignedToUser(user);

		List<User> users = userService.getAllUsers();

		mv.addObject("tasks", tasks);
		System.out.println(tasks);
		mv.addObject("users", users);
		return mv;

	}

	// this route will return the details of a specific task ,
	// it will take task id as parameter
	@GetMapping(path = "/{task_id}")
	public ResponseEntity<?> viewTask(@PathVariable(name = "task_id") Integer task_id) {
		Task task = taskService.getTaskById(task_id);
		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
	}

	// this route will delete an existing task
	// it will take task_id as parameter
	@PostMapping(path = "/deleteTask")
	public String deleteTask(@ModelAttribute DeleteTaskDTO deleteTaskDTO,RedirectAttributes redirectAttributes) {
		taskService.deleteTask(deleteTaskDTO.getTask_id());
		redirectAttributes.addFlashAttribute("message","task-deleted");

		return "redirect:/task/";
	}
}
