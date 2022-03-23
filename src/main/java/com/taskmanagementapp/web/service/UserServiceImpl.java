package com.taskmanagementapp.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.model.entity.User;
import com.taskmanagementapp.model.repository.UserRepository;
import com.taskmanagementapp.web.exceptions.UserNotFoundException;

/**
 * User Service Implementation
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Function to create new User
	 */
	public User createUser(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new Error("user already exists");
		}
		userRepository.save(user);
		return user;
	}

	/**
	 * Function to get List of all Users
	 */
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Function to get User By User ID
	 */
	@Override
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found"));
	}
	
	/**
	 * Function to get User by Username
	 */
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User Not Found"));
		}
}
