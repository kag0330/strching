package com.stretching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.ui.Model;

import com.stretching.dto.YoutubeDto;
import com.stretching.service.YoutubeService;

@Controller
public class ProgramController {
	
	@Autowired
	private YoutubeService youtubeService;

	@GetMapping("/program")
	public String program(Model model) {
		List<YoutubeDto> youtubeList = youtubeService.YoutubeListPrint();
		model.addAttribute("lists", youtubeList);
		System.out.println(youtubeList.toString());
		return "program";
	}

	@GetMapping("/youtubeview")
	public String youtubeview() {
		return "youtubeview";
	}

}
