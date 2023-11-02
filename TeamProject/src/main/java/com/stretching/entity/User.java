package com.stretching.entity;



import com.stretching.role.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
	@Id
	@Column(name = "USER_ID")
	private String id;
	
	@Column(name = "USER_PW")
	private String pw;
	
	@Column(name = "USER_NAME")
	private String name;
	
	@Column(name = "USER_PHONE")
	private String phone;
	
	@Column(name = "USER_ROLE")
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@Builder
	public User(String id, String pw, String name, String phone, UserRole role) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.role = role;
	}
	
}
