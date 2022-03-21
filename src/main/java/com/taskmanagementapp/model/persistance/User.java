package com.taskmanagementapp.model.persistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude =  {"createdTasks","assignedTasks","comments","password"})
@Entity
@Table(name = "`user`")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;

	private String name;
	private String email;
	private String username;
	@JsonIgnore
	private String password;

	@JsonBackReference
	@OneToMany(mappedBy = "assignedTo")
	List<Task> createdTasks = new ArrayList<Task>();

	@JsonBackReference
	@OneToMany(mappedBy = "createdBy")
	List<Task> assignedTasks = new ArrayList<Task>();
	
	@JsonBackReference
	@OneToMany(mappedBy="user")
	List<Comment> comments=new ArrayList<Comment>();
}
