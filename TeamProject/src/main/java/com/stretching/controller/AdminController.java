package com.stretching.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stretching.dto.YoutubeDto;
import com.stretching.repository.YoutubeRepository;
import com.stretching.service.JsonListService;
import com.stretching.service.UserService;
import com.stretching.service.YoutubeService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private YoutubeService youtubeService;
	@Autowired
	private JsonListService jsonListService;
	@Autowired 
	private UserService userService;

	@GetMapping()
	public String admin(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		return "/adminPage/admin";
	}
	
	@GetMapping("/search")
	public String uSearch(Authentication auth, Model model) throws IOException {
		userService.logincheck(auth, model);
		return "adminPage/search";
	}
	
	@GetMapping("/upload")
	public String uUpload(Authentication auth, Model model) {
		userService.logincheck(auth, model);
		List<String> file = jsonListService.getJsonList();
		model.addAttribute("files", file);
		return "adminPage/upload";
	}
	
	@GetMapping("/delete")
	public String uDelete(@PageableDefault(page = 1) Pageable pageable, Model model, Authentication auth) {
		userService.logincheck(auth, model);
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
		return "adminPage/delete";
	}
	@DeleteMapping("/delete/{seq}")
	public ResponseEntity<String> deleteItem(@PathVariable("seq")Long seq){
		if(youtubeService.delete(seq)) {
			return ResponseEntity.ok("삭제 완료");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
		}
	}
	
	

	@RequestMapping(value = "/youtubeDBUpload_ok", method = RequestMethod.POST, consumes = "application/json;")
	public ResponseEntity<String> uploadData(@RequestBody List<YoutubeDto> youtubeList) throws JsonProcessingException {
		System.out.println(youtubeList.toString());
		youtubeService.youtubeListSave(youtubeList);
		return ResponseEntity.ok("Data uploaded successfully");
	}
	
	
	
}
