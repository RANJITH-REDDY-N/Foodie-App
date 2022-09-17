package com.cgi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="users")
public class User {

	@Id
	@Column(length= 50,nullable=false)
	private String username;
	@Column(length= 50,nullable=false)
	private String email;
	@Column(length= 30,nullable=false)
	private String phone;
	@Column(length= 30,nullable=false)
	private String password;
	@Column(length=10,columnDefinition = "boolean default false",nullable= false)
	private boolean status;
	
	public User(String username, String email, String phone, String password, boolean status) {
		super();
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.status = status;
	}
	public User() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", phone=" + phone + ", password=" + password + "]";
	}
	public void setStatus(boolean status) {
		this.status= status;
	}
	public boolean isStatus() {
		return status;
	}
	
}
