package com.taskmanagementapp.web.exceptions;

/**
 * Task Not Found Exception
 * 
 * @author Ayush
 */
public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(String arg) {
		super(arg);
	}
}
