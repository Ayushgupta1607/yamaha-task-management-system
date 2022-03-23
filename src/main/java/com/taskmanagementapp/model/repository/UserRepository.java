package com.taskmanagementapp.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagementapp.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String Username);
	public Boolean existsByUsername(String Username);
}
