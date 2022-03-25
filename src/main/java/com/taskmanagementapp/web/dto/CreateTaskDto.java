package com.taskmanagementapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create Task Request DTO
 * 
 * @author Ayush
 * @version 0.1, 25 March 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {
	private String title;
	private String description;
	private Integer createdById;
	private Integer assignedToId;
}
