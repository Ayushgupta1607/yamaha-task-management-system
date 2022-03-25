package com.taskmanagementapp.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskmanagementapp.web.model.entity.Task;
import com.taskmanagementapp.web.model.entity.User;

/**
 * Task Repository Interface
 * 
 * @author Ayush
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
	public List<Task> findByCreatedByOrAssignedTo(User createdBy, User assignedTo);

	public List<Task> findByCreatedByAndIsDeleted(User createdBy, boolean isDeleted);

	public List<Task> findByAssignedToAndIsDeleted(User assignedTo, boolean isDeleted);

	@Query("SELECT task FROM Task task WHERE (task.createdBy = ?1 OR task.assignedTo = ?2) AND task.isDeleted= ?3")
	public List<Task> findTaskCreatedByOrAssignedToAndIsDeleted(User createdBy, User assignedTo, boolean isDeleted);
}
