package com.taskmanagementapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagementapp.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
