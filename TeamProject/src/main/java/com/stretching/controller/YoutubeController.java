package com.stretching.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stretching.KeyValuePair;
import com.stretching.entity.Youtube;
import com.stretching.service.YoutubeService;

@Controller
public class YoutubeController {
	
	@PostMapping("/admin_youtubeDBUpload_ok")
    public ResponseEntity<String> uploadData(@RequestBody List<Youtube> items) throws JsonProcessingException {
		for(Youtube item:items) {
			List<KeyValuePair> kvp = item.toKeyValuePairs();
			Map<String, String> map = new HashMap<>();
			for(KeyValuePair pair : kvp) {
				map.put(pair.getKey(), pair.getValue());
			}
			DBUpload(map);
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
	
	public void DBUpload(Map<String, String>data) {
		Youtube youtube = new Youtube();
		youtube.setTitle(String.valueOf(data.get("title")));
		youtube.setUrl(String.valueOf(data.get("url")));
		youtube.setIframe_url(String.valueOf(data.get("iframe_url")));
		youtube.setImg_url(String.valueOf(data.get("img_url")));
		youtube.setUploader(String.valueOf(data.get("uploader")));
		youtube.setCnt(0);
		youtube.setUpload_date(new Date());
		YoutubeService ys = new YoutubeService();
		ys.save(youtube);
	}
}


