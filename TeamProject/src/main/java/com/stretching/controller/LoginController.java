package com.stretching.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.stretching.dto.LoginRequest;
import com.stretching.dto.UserDto;
import com.stretching.dto.YoutubeDto;
import com.stretching.entity.User;
import com.stretching.service.UserService;
import com.stretching.service.YoutubeService;

import jakarta.servlet.http.HttpSession;



@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	
	@GetMapping("/signin")
	public String login1(Model model) {
		model.addAttribute("loginRequest", new LoginRequest());
		return "signin";
	}
	
	@GetMapping("/signup")
	public String signup(Model model, UserDto userDto) {
		model.addAttribute("userDto", userDto);
		return "signup";
	}
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	
	@GetMapping("/find_id")
	public String findid() {
		return "find_id";
	}
	@GetMapping("/find_pass")
	public String findpass() {
		return "find_pass";
	}
	@GetMapping("/authentication_fail")
	public String authentication_fail() {
		return "authentication_fail";
	}
	@GetMapping("/authorization_fail")
	public String authorizaion() {
		return "authorization_fail";
	}
	
	@PostMapping("signup/ok")
	public String signupOk(@ModelAttribute("userDto") UserDto userDto, Model model) {
		if(userService.UserSave(userDto) == true) {
			return "redirect:/signin";
		}else {
			model.addAttribute("useId", userDto.getId());
			return "redirect:/signup";
		}
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam("id")String id) {
		return userService.UserIdCheck(id);
	}
}
