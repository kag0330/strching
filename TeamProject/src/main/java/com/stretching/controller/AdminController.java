package com.stretching.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stretching.dto.YoutubeDto;
import com.stretching.repository.YoutubeRepository;
import com.stretching.service.JsonListService;
import com.stretching.service.YoutubeService;

@Controller
public class AdminController {
	@Autowired
	private YoutubeService youtubeServcie;
	@Autowired
	private JsonListService jsonListService;

	@GetMapping("/admin")
	public String admin() {
		
		return "/adminPage/admin";
	}

	@RequestMapping(value = "/admin/youtubeDBUpload_ok", method = RequestMethod.POST, consumes = "application/json;")
	public ResponseEntity<String> uploadData(@RequestBody List<YoutubeDto> youtubeList) throws JsonProcessingException {
		System.out.println(youtubeList.toString());
		youtubeServcie.youtubeListSave(youtubeList);
		return ResponseEntity.ok("Data uploaded successfully");
	}
	
	@GetMapping("/admin/youtubeUpload")
	public String getJsonFiles(Model model) throws IOException {
		List<String> file = jsonListService.getJsonList();
		model.addAttribute("files", file);
		return "adminPage/youtubeDBUpload";
	}
	
}
