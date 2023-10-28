package com.stretching.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stretching.dto.YoutubeDto;
import com.stretching.entity.Youtube;
import com.stretching.repository.YoutubeRepository;

@Controller
public class YoutubeController {

	@Autowired
	private YoutubeRepository youtubeRepository;

	@PostMapping("/admin_youtubeDBUpload_ok")
	public ResponseEntity<String> uploadData(@RequestBody List<YoutubeDto> items) throws JsonProcessingException {
		System.out.println(items.toString());
		for (YoutubeDto youtubeDto : items) {
			youtubeRepository.save(Youtube.builder().url(youtubeDto.getUrl()).title(youtubeDto.getTitle())
					.iframeurl(youtubeDto.getIframeUrl()).imgurl(youtubeDto.getImgUrl()).type(youtubeDto.getType())
					.uploader(youtubeDto.getUploader()).uploadDate(new Date()).cnt(0L).build());
		}
		return ResponseEntity.ok("Data uploaded successfully");
	}

	@GetMapping("/admin_youtubeUpload")
	public String getJsonFiles(Model model) throws IOException {
		List<String> fileNames = new ArrayList<>();

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath:/static/json/*.json");

		for (Resource resource : resources) {
			String filename = resource.getFilename();
			fileNames.add(filename);
		}

		model.addAttribute("files", fileNames);

		return "adminPage/youtubeDBUpload";
	}

}
