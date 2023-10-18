package com.stretching.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/messageBoard")
	public String messageBoard() {
		return "messageBoard";
	}

	@GetMapping("/write")
	public String write() {
		return "write";
	}
	@GetMapping("/write-content")
	public String writecontent() {
		return "write-content";
		
	}
	@GetMapping("/write-edit")
	public String writeedit() {
		return "write-edit";
	}
	
	
	@GetMapping("/m_myRoutine")
	public String myroutine() {
		return "m_myRoutine";
	}

}
