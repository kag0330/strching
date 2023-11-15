package com.stretching.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.stretching.dto.LoginRequest;
import com.stretching.dto.UserDto;
import com.stretching.dto.YoutubeDto;
import com.stretching.entity.User;
import com.stretching.entity.Youtube;
import com.stretching.repository.UserRepository;
import com.stretching.role.UserRole;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder pe;

	/**
	 * 유저 회원가입 pe로 pw암호화
	 */
	public boolean UserSave(UserDto userDto) {
		if (userRepository.findById(userDto.getId()).isEmpty()) {
			userRepository.save(userDto.toEntity(pe.encode(userDto.getPw())));
			return true;

		} else {
			return false;
		}
	}

	/**
	 * 유저 아이디 체크 아이디가 중복이 아니면 true 리턴 아이디가 중복이면 false 리턴
	 */
	public boolean UserIdCheck(String id) {
		if (userRepository.findById(id).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * */
	public User getLoginUser(String loginId) {
		if (loginId == null)
			return null;

		Optional<User> user = userRepository.findById(loginId);
		if (user.isEmpty())
			return null;

		return user.get();
	}

	public User login(LoginRequest req) {
		Optional<User> opUser = userRepository.findById(req.getLoginId());

		if (opUser.isEmpty()) {
			return null;
		}

		User user = opUser.get();
		if (!pe.matches(req.getPassword(), user.getPw())) {
			return null;
		}

		return user;
	}

	public void logincheck(Authentication auth, Model model) {
		if (auth != null) {
			User loginUser = getLoginUser(auth.getName());
			if (loginUser != null) {
				model.addAttribute("loginUser", loginUser.getId());
				model.addAttribute("userRole", loginUser.getRole());
			}
		}
	}

	public boolean delete(String userid) {
		try {
			userRepository.deleteById(userid);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
			return false;
	}

	public List<User> paging() {
		List<User> pages = userRepository.findAll();
		return pages;
		
	}
	
	@Transactional
	public boolean putRole(String userid) {
		User user = userRepository.findById(userid).get();
		if(user != null) {
			user.changeRoleAdmin();
			return true;
		}else {
			return false;
		}
	}

}
