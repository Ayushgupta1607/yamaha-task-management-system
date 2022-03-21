package com.taskmanagementapp.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagementapp.model.exceptions.UserNotFoundException;
import com.taskmanagementapp.model.persistance.User;
import com.taskmanagementapp.model.persistance.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new Error("user already exists");
		}
		System.out.println(user);
		userRepository.save(user);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found"));
	}
	
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User Not Found"));
		}
}
