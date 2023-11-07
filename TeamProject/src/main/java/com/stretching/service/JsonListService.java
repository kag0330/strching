package com.stretching.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

@Service
public class JsonListService {
	public List<String> getJsonList() {
		List<String> fileNames = new ArrayList<>();
		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resolver.getResources("classpath:/static/json/*.json");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Resource resource : resources) {
			String filename = resource.getFilename();
			fileNames.add(filename);
		}

		
		return fileNames;
	}
}