package com.taskmanagementapp.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagementapp.web.model.entity.User;

/**
 * User Repository Interface
 * 
 * @author Ayush
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);

	public Boolean existsByUsername(String username);
}
