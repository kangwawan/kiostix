package com.birumerah.kiostix.dto;

public class UserDTO {
	private Integer userId;
	private String username;
	private String password;
	private String name;
	private Integer roleId;
	private Integer marketplace; // GROUP_ID
	private boolean active;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMarketplace() {
		return marketplace;
	}
	public void setMarketplace(Integer marketplace) {
		this.marketplace = marketplace;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
