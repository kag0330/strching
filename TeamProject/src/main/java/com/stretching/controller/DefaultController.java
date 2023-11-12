package com.stretching.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stretching.dto.YoutubeDto;
import com.stretching.entity.User;
import com.stretching.service.UserService;
import com.stretching.service.YoutubeService;

@Controller
@RequestMapping(value = {"", "/"})
public class DefaultController {
	@Autowired
	private UserService userService;
	@Autowired
	private YoutubeService youtubeService;
	
	@GetMapping
	public String index(Model model, Authentication auth) {
		userService.logincheck(auth, model);
		List<YoutubeDto> youtubeListDate = youtubeService.youtubeListPrintByDate();
		List<YoutubeDto> youtubeListCnt = youtubeService.youtubeListPrintByCnt();
		model.addAttribute("listsDate",youtubeListDate);
		model.addAttribute("listsCnt",youtubeListCnt);
		return "index";
	}
}
