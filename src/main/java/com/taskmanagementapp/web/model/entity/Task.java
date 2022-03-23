package com.taskmanagementapp.web.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Task Entity  
 * 
 * @author Ayush
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")

public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private String status;

	@Column(name = "started_date")
	private Date startedDate;

	@Column(name = "completion_date")
	private Date completionDate;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_created_by")
	private User createdBy;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_assigned_to")
	private User assignedTo;

	@JsonManagedReference
	@OneToMany(mappedBy = "task")
	List<Comment> comments = new ArrayList<Comment>();

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();

	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	public Task(String title, String description, String status, User createdBy, User assignedTo) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.assignedTo = assignedTo;
		this.createdBy = createdBy;
		this.isDeleted = false;
		this.createdAt = new Date();
	}

}
