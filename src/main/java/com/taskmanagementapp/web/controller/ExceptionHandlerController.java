package com.taskmanagementapp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagementapp.web.exceptions.TaskNotFoundException;
import com.taskmanagementapp.web.exceptions.UserNotFoundException;

/**
 * Controller to handle exception occurred in application
 * @author Ayush
 *
 */
@Controller
public class ExceptionHandlerController {
	private Logger logger = LoggerFactory.logger(AccountController.class);

	/**
	 * Handles UserNotFound and TaskNotFound Exceptions
	 */
	 @ExceptionHandler({UserNotFoundException.class,TaskNotFoundException.class})
	 public void conflict(HttpServletRequest req, Exception ex) {
		  logger.error("Request: " + req.getRequestURL() + " raised " + ex);
	 }
}
