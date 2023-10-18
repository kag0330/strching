package com.stretching.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/signin")
	public String logn() {
		return "signin";
	}
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@GetMapping("/find_id")
	public String findid() {
		return "find_id";
	}
	@GetMapping("/find_pass")
	public String findpass() {
		return "find_pass";
}
	@GetMapping("/myPage")
	public String mypage() {
		return "myPage";
	}
}
