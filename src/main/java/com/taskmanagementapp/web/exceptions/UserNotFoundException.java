package com.taskmanagementapp.web.exceptions;

/**
 * User Not Found Exception
 * 
 * @author Ayush
 *
 */
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String arg) {
		super(arg);
	}
}
