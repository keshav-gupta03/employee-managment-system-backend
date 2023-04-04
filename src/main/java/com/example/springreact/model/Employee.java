package com.example.springreact.model;
import lombok.*;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Entity
@Table(name="employee")
@Setter
@Getter
@ToString
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email_id" , unique = true)
	private String emailId;
	
	@Column(name="designation")
	private String designation;

	@Column(name="password" , columnDefinition = "integer default 12345")
	private String password;
	
	@Column(name="profile_image")
	private String profileImage;
	
	public Employee() {
		super();
	}
	

	public Employee(long id, String firstName, String lastName, String emailId, String password, String designation,
			String profileImage) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.designation = designation;
		this.profileImage = profileImage;
	}


}
