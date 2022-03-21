package com.taskmanagementapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatetaskDTO {
	private Integer task_id;
	private String description;
	private Boolean status;
	private Integer assigned_to_id;
	private String comments;
}
