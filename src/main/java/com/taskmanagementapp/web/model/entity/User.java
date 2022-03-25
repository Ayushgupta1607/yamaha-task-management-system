package com.taskmanagementapp.web.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User Entity
 * 
 * @author Ayush
 * @version 0.1, 25 March 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "createdTasks", "assignedTasks", "comments", "password" })
@Entity
@Table(name = "`user`")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "name")
	private String name;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "username", unique = true)
	private String username;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@JsonBackReference
	@OneToMany(mappedBy = "assignedTo")
	private List<Task> createdTasks = new ArrayList<>();

	@JsonBackReference
	@OneToMany(mappedBy = "createdBy")
	private List<Task> assignedTasks = new ArrayList<>();

	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private List<Comment> comments = new ArrayList<>();
}
