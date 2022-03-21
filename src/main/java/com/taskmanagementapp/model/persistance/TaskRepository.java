package com.taskmanagementapp.model.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
public List<Task> findByCreatedByOrAssignedTo(User created_by,User assigned_to);
public List<Task> findByCreatedBy(User created_by);
public List<Task> findByAssignedTo(User assigned_to);

}
