package com.taskmanagementapp.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.web.exceptions.UserNotFoundException;
import com.taskmanagementapp.web.model.entity.User;
import com.taskmanagementapp.web.repository.UserRepository;

/**
 * User Service Implementation to Create and Read User
 * 
 * @author Ayush
 * @version 0.1, 25 March 2022
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.logger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * Method to create new user
	 * 
	 * @param user User to be created
	 * @return User saved user
	 */
	public User createUser(User user) {
		logger.debug("Create User Method of User Service is called with Parameters user:" + user);
		// Invoke User Repository to create new user if not exist already
		if (Boolean.TRUE.equals(userRepository.existsByUsername(user.getUsername()))) {
			throw new UserNotFoundException("user already exists");
		}
		userRepository.save(user);
		logger.debug("User Created Successfully user:" + user);
		return user;
	}

	/**
	 * Method to retrieve list of all users
	 * 
	 * @return List<User> List of all users
	 */
	@Override
	public List<User> getAllUsers() {
		logger.debug("Get All Users Method of User Service is called");
		// Invoke User Repository to find all users
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
		// Invoke user repository to find user by id
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
		// Invoke user repository to find user by username
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}
}
