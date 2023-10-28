package com.stretching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stretching.dto.UserDto;
import com.stretching.entity.User;
import com.stretching.repository.UserRepository;
import com.stretching.role.UserRole;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder pe;
	
	/** 
	 * CRUD 구현
	 * */
	
	public boolean UserSave(UserDto userDto) {
		if(userRepository.findById(userDto.getId()).isEmpty()) {
			userRepository.save(User.builder()
					.id(userDto.getId())
					.pw(pe.encode(userDto.getPw()))
					.name(userDto.getName())
					.phone(userDto.getPhone())
					.role(UserRole.USER)
					.build());
			return true;
			
		}else {
			return false;
		}
	}
	public boolean UserIdCheck(String id) {
		System.out.println("USER_ID_CHECK");
		if(userRepository.findById(id).isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
