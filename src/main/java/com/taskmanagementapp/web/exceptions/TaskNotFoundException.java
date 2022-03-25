package com.taskmanagementapp.web.exceptions;

/**
 * Task Not Found Exception
 * 
 * @author Ayush
 * @version 0.1, 25 March 2022
 */
public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(String arg) {
		super(arg);
	}
}
