package com.taskmanagementapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Register User Request DTO
 * 
 * @author Ayush
 * @version 0.1, 25 March 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
	private String name;
	private String username;
	private String email;
	private String password;
}
