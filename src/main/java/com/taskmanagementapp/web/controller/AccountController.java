package com.taskmanagementapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taskmanagementapp.dto.RegisterUserDTO;
import com.taskmanagementapp.model.persistance.User;
import com.taskmanagementapp.model.service.UserService;

@Controller
public class AccountController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@GetMapping(path = "/login")
	public String appLogin() {
		return "login";
	}

	@GetMapping(path = "home")
	public String home() {
		return "redirect:task/";
	}
	@PostMapping(path = "/register")
	@ResponseBody
	public ResponseEntity<?> Register(@RequestBody RegisterUserDTO registerUserDto) {
		User user = new User();
		user.setName(registerUserDto.getName());
		user.setEmail(registerUserDto.getEmail());
		user.setUsername(registerUserDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
		userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping(path = "/list")
	public List<User> ListUsers() {
		return userService.getAllUsers();
	}
}
