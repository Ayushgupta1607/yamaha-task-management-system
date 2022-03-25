package com.taskmanagementapp.web.exceptions;

/**
 * User Not Found Exception
 * 
 * @author Ayush
 * @version 0.1, 25 March 2022
 */
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String arg) {
		super(arg);
	}
}
