package com.taskmanagementapp.model.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String Username);
	public Boolean existsByUsername(String Username);
}
