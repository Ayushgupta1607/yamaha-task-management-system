package com.taskmanagementapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taskmanagementapp.model.entity.User;
import com.taskmanagementapp.web.dto.RegisterUserDto;
import com.taskmanagementapp.web.service.UserService;

/**
 * Account Controller Configuration
 */
@Controller
public class AccountController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	/**
	 * Get the login page of the application
	 * @return display login page
	 */
	@GetMapping(path = "/login")
	public String appLogin() {
		return "login";
	}

	/**
	 * Get the homepage of the application
	 * @return redirect to homepage
	 */
	@GetMapping(path = "")
	public String home() {
		return "redirect:task/";
	}

	/**
	 * Create a new User
	 * @param registerUserDto Request DTO to create new User
	 * @return ResponseEntity<User>
	 */
	@PostMapping(path = "/register")
	@ResponseBody
	public ResponseEntity<?> Register(@RequestBody RegisterUserDto registerUserDto) {
		User user = new User();
		user.setName(registerUserDto.getName());
		user.setEmail(registerUserDto.getEmail());
		user.setUsername(registerUserDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
		userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

}
