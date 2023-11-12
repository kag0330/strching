package com.stretching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stretching.service.UserService;

@Controller
public class BoardController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/messageBoard")
	public String messageBoard(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "messageBoard";
	}

	@GetMapping("/write")
	public String write(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "write";
	}
	@GetMapping("/write-content")
	public String writecontent(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "write-content";
		
	}
	@GetMapping("/write-edit")
	public String writeedit(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "write-edit";
	}
}
