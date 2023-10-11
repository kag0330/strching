package com.stretching.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/messageBoard")
	public String messageBoard() {
		return "messageBoard";
	}
}
