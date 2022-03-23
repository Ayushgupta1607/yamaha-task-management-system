package com.taskmanagementapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {
	private String title;
	private String description;
	private Integer created_by_id;
	private Integer assigned_to_id;
}
