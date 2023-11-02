package com.stretching.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stretching.dto.LoginRequest;
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
	 * 유저 회원가입
	 * pe로 pw암호화
	 * */
	public boolean UserSave(UserDto userDto) {
		if(userRepository.findById(userDto.getId()).isEmpty()) {
			userRepository.save(userDto.toEntity(pe.encode(userDto.getPw())));
			return true;
			
		}else {
			return false;
		}
	}
	
	/**
	 * 유저 아이디 체크 
	 * 아이디가 중복이 아니면 true 리턴
	 * 아이디가 중복이면 false 리턴
	 * */
	public boolean UserIdCheck(String id) {
		System.out.println("USER_ID_CHECK");
		if(userRepository.findById(id).isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 * */
	public User getLoginUser(String loginId) {
		if(loginId == null) return null;
		
		Optional<User> user = userRepository.findById(loginId);
		if(user.isEmpty()) return null;
		
		return user.get();
	}
	
	public User login(LoginRequest req) {
		Optional<User> opUser = userRepository.findById(req.getLoginId());
		
		if(opUser.isEmpty()) {
			return null;
		}
		
		User user = opUser.get();
		if(!pe.matches(req.getPassword(), user.getPw())) {
			return null;
		}
		
		return user;
	}
	
	
}
