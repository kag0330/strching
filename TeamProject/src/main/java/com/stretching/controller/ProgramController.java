package com.stretching.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.stretching.dto.YoutubeDto;
import com.stretching.entity.User;
import com.stretching.repository.YoutubeRepository;
import com.stretching.service.UserService;
import com.stretching.service.YoutubeService;

@Controller
public class ProgramController {

	@Autowired
	private YoutubeService youtubeService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/program")
	public String programGet(@PageableDefault(page = 1) Pageable pageable, Model model, Authentication auth) {
		if (auth != null) {
			User loginUser = userService.getLoginUser(auth.getName());
			if (loginUser != null) {
				model.addAttribute("loginUser", loginUser.getId());
			}
		}
		Page<YoutubeDto> youtubePages = youtubeService.paging(pageable);
		int blockLimit = 3;
		int startPage = ((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit)));
		int endPage = Math.min((startPage + blockLimit - 1), youtubePages.getTotalPages());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String today = LocalDate.now().format(formatter);
		model.addAttribute("youtubePages", youtubePages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("today", today);
		return "program";
	}
	
	@PostMapping("/program")
	public String programPost(@RequestParam("search") String searchKey, @PageableDefault(page = 1) Pageable pageable,
			Model model, Authentication auth) {
		if (auth != null) {
			User loginUser = userService.getLoginUser(auth.getName());
			if (loginUser != null) {
				model.addAttribute("loginUser", loginUser.getId());
			}
		}
		Page<YoutubeDto> youtubePages = youtubeService.pagingSearch(pageable, searchKey);
		int blockLimit = 3;
		int startPage = ((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit)));
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
	public String youtubeview(@PathVariable("seq") Long seq, Model model) {
		System.out.println(seq);
		YoutubeDto youtubeDto = youtubeService.youtubePrint(seq);
		youtubeService.updateCnt(seq);
		System.out.println(youtubeDto);
		model.addAttribute("youtube", youtubeDto);
		return "youtubeview";
	}
	@PostMapping("/bookmark")
	@ResponseBody
	public String bookmark(@RequestParam("seq") Long seq, Authentication auth, Model model) {
		if (auth != null) {
			User loginUser = userService.getLoginUser(auth.getName());
			if (loginUser != null) {
				youtubeService.onBookmark(loginUser, seq);
				return "OK";
			}
		}else {
			return "로그인 해주세요.";
		}
		return "done";
	}
	@PostMapping("/checkBookmark")
	@ResponseBody
	public boolean checkBookmark(@RequestParam("seq") Long seq, Authentication auth, Model model) {
		System.out.println("1");
		if (auth != null) {
			User loginUser = userService.getLoginUser(auth.getName());
			if (loginUser != null) {
				System.out.println("2");
				return youtubeService.checkBookmark(loginUser, seq);
			}
		}
		System.out.println("3");
		return false;
	}
}
