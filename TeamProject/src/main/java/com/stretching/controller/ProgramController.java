package com.stretching.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.stretching.entity.Youtube;
import com.stretching.service.YoutubeService;


@Controller
public class ProgramController {

	@GetMapping("/program")

	public String program() {
		return "program";
}

	public String program(Model model) {
		YoutubeService ys = new YoutubeService();
		List<String> lists = ys.select_all();
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "program";
	}
	

}
