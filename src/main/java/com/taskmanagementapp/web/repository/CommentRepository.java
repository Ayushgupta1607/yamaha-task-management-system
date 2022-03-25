package com.taskmanagementapp.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagementapp.web.model.entity.Comment;

/**
 * Comment Repository Interface
 * 
 * @author Ayush
 * @version 0.1, 25 March 2022
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
