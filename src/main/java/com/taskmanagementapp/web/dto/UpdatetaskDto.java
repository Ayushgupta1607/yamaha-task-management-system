package com.taskmanagementapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Update Task Request DTO
 * 
 * @author Ayush
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatetaskDto {
	private Integer taskId;
	private String description;
	private Boolean status;
	private Integer assignedToId;
	private String[] comments;
}
