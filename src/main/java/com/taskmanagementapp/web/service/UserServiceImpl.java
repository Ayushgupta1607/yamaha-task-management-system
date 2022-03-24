package com.taskmanagementapp.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.web.controller.TaskController;
import com.taskmanagementapp.web.exceptions.UserNotFoundException;
import com.taskmanagementapp.web.model.entity.User;
import com.taskmanagementapp.web.repository.UserRepository;

/**
 * User Service Implementation
 * 
 * @author Ayush
 * 
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.logger(TaskController.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * Method to create new user
	 * 
	 * @param user User to be created
	 * @return User saved user
	 */
	public User createUser(User user) {
		logger.info("Create User Method of User Service is called with Parameters user:" + user);
		if (Boolean.TRUE.equals(userRepository.existsByUsername(user.getUsername()))) {
			throw new UserNotFoundException("user already exists");
		}
		userRepository.save(user);
		logger.info("User Created Successfully user:" + user);
		return user;
	}

	/**
	 * Method to retrieve list of all users
	 * 
	 * @return List<User> List of all users
	 */
	@Override
	public List<User> getAllUsers() {
		logger.info("Get All Users Method of User Service is called");
		return userRepository.findAll();
	}

	/**
	 * Method to retrieve user by id
	 * 
	 * @param id Id of User
	 * @return User
	 */
	@Override
	public User getUserById(Integer id) {
		logger.info("Get User By Id Method of User Service is called with parameter id:" + id);
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found"));
	}

	/**
	 * Method to retrieve user by username
	 * 
	 * @param username Username of the user
	 * @return User
	 */
	@Override
	public User getUserByUsername(String username) {
		logger.info("Get User By Username Method of User Service is called with parameter username:" + username);
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}
}
