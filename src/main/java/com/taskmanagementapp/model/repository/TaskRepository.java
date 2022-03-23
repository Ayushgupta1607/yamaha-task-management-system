package com.taskmanagementapp.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagementapp.model.entity.Task;
import com.taskmanagementapp.model.entity.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {
public List<Task> findByCreatedByOrAssignedTo(User created_by,User assigned_to);
public List<Task> findByCreatedByAndIsDeleted(User created_by,boolean isDeleted);
public List<Task> findByAssignedToAndIsDeleted(User assigned_to,boolean isDeleted);
public List<Task> findtaskByCreatedByOrAssignedToAndIsDeleted(User created_by,User assigned_to,boolean isDeleted);
}
