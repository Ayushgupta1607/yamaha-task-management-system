package com.taskmanagementapp.web.service;

import java.util.List;

import com.taskmanagementapp.web.model.entity.User;

/**
 * User Service Interface
 * 
 * @author Ayush
 */
public interface UserService {
	/**
	 * Method to create new user
	 * 
	 * @param user User to be created
	 * @return User saved user
	 */
	public User createUser(User user);

	/**
	 * Method to retrieve list of all users
	 * 
	 * @return List<User> List of all users
	 */
	public List<User> getAllUsers();

	/**
	 * Method to retrieve user by id
	 * 
	 * @param id Id of User
	 * @return User
	 */
	public User getUserById(Integer id);

	/**
	 * Method to retrieve user by username
	 * 
	 * @param username Username of the user
	 * @return User
	 */
	public User getUserByUsername(String username);
}
