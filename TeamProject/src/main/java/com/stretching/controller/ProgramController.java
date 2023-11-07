package com.stretching.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.stretching.dto.YoutubeDto;
import com.stretching.service.YoutubeService;

@Controller
public class ProgramController {
	
	@Autowired
	private YoutubeService youtubeService;

	@GetMapping("/program")
	public String program(@PageableDefault(page = 1)Pageable pageable, Model model) {
		Page<YoutubeDto> youtubePages = youtubeService.paging(pageable);
		int blockLimit=3;
		int startPage = ((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit )));
		int endPage = Math.min((startPage + blockLimit - 1), youtubePages.getTotalPages());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String today = LocalDate.now().format(formatter);
		model.addAttribute("youtubePages", youtubePages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("today", today);
		return "program";
	}

	@GetMapping("/youtubeview/{seq}")
	public String youtubeview(@PathVariable("seq")Long seq, Model model) {
		System.out.println(seq);
		YoutubeDto youtubeDto = youtubeService.YoutubePrint(seq);
		youtubeService.updateCnt(seq);
		System.out.println(youtubeDto);
		model.addAttribute("youtube", youtubeDto);
		return "youtubeview";
	}

}
