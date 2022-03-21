package com.taskmanagementapp.model.persistance;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude =  {"comments"})
@Entity
@Table(name = "task")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer task_id;
	
	private String title;
	private String description;
	private String status;
	
	@Column(name="started_date")
	private Date startedDate;
	@Column(name="completion_date")
	private Date completionDate;
	
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
	List<Comment> comments=new ArrayList<Comment>();
	
	public Task(String title, String description, String status, User createdBy, User assignedTo) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.assignedTo = assignedTo;
		this.createdBy = createdBy;
	}
	
	

}
