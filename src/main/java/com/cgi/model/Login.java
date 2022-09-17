package com.cgi.model;


public class Login {
	private String username;
	private String password;
	private boolean status;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Login() {
		super();
	}
	public Login(String username, String password, boolean status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", status=" + status + "]";
	}
	
	
}
