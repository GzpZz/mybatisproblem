package com.problem.entity;

/**
 * @author Administrator
 *
 */
public class User {
	private int id;//”√ªßid
	private String username;//Í«≥∆
	private String password;
	
	/*public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	 
	
}
