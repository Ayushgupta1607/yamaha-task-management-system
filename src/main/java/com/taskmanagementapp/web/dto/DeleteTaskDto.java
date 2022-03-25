package com.taskmanagementapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Delete Task Request DTO
 * 
 * @author Ayush
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTaskDto {
	private Integer taskId;
}
