package com.taskmanagementapp;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Task Management Application initialization
 * @author Ayush
 */
@SpringBootApplication
public class TaskManagementApplication {
	private static Logger logger = LoggerFactory.logger(TaskManagementApplication.class);

	public static void main(String[] args) {
		logger.info("Application Started");
		SpringApplication.run(TaskManagementApplication.class, args);
	}

}
