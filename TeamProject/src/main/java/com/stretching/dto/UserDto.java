package com.stretching.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.stretching.entity.User;
import com.stretching.role.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserDto {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private UserRole role;
	
	public UserDto() {
		
	}
	
	public UserDto(User entity) {
		this.id = entity.getId();
		this.pw = entity.getPw();
		this.name = entity.getName();
		this.phone = entity.getPhone();
		this.role = entity.getRole();
	}
	
	public User toEntity() {
		return User.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.phone(phone)
				.role(role)
				.build();
	}

}
