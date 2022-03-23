package com.taskmanagementapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatetaskDto {
	private Integer task_id;
	private String description;
	private Boolean status;
	private Integer assigned_to_id;

	private String[] comments;
}
