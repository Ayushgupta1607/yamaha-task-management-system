package com.taskmanagementapp.web.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Comment Entity 
 * 
 * @author Ayush
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "task")
@Entity
@Table(name = "comment")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Column(name="comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;

	@Column(name="message")
	private String message;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_task")
	private Task task;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "fk_user")
	private User user;

	@Column(name = "created_at")
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	public Comment(String message, User user, Task task) {
		super();
		this.message = message;
		this.task = task;
		this.user = user;

	}

}
