package com.stretching.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stretching.dto.YoutubeDto;
import com.stretching.entity.User;
import com.stretching.service.UserService;
import com.stretching.service.YoutubeService;

@Controller
@RequestMapping(value = "/mypage")
public class MyPageController {
	@Autowired
	private YoutubeService youtubeService;
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String myPage(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "myPage/mypage";
	}
	
	@GetMapping("/bookmark")
	public String bookmark(@PageableDefault(page = 1) Pageable pageable, Model model, Authentication auth) {
	    if (auth != null) {
	        User loginUser = userService.getLoginUser(auth.getName());
	        if (loginUser != null) {
	            Page<YoutubeDto> youtubePages = youtubeService.pagingUserBookmark(pageable, loginUser);
	            int blockLimit = 3;
	            int startPage = ((int) Math.ceil(((double) youtubePages.getNumber() + 1) / blockLimit));
	            int endPage = Math.min((startPage + blockLimit - 1), youtubePages.getTotalPages());
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	            String today = LocalDate.now().format(formatter);

	            model.addAttribute("loginUser", loginUser.getId());
	            model.addAttribute("youtubePages", youtubePages);
	            model.addAttribute("startPage", startPage);
	            model.addAttribute("endPage", endPage);
	            model.addAttribute("today", today);
	        }
	    }
	    return "/myPage/bookmark";
	}

}
