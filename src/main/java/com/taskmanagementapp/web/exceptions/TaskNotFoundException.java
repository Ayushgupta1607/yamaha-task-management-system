package com.taskmanagementapp.web.exceptions;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	 public TaskNotFoundException(String arg) {
		super(arg);
	}
}
