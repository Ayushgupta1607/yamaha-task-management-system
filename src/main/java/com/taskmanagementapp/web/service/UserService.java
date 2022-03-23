package com.taskmanagementapp.web.service;

import java.util.List;

import com.taskmanagementapp.model.entity.User;


public interface UserService {
	public User createUser(User user);
	public List<User> getAllUsers();
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
}
