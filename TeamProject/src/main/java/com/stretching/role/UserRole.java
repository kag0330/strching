package com.stretching.role;

public enum UserRole {
	USER("USER"),
	ADMIN("ADMIN");
	String role;
	
	UserRole(String role){
		this.role = role;
	}
	public String value() {
		return role;
	}
}
