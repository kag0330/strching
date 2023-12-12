package com.stretching;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stretching.repository.UserRepository;
import com.stretching.repository.YoutubeRepository;

@SpringBootTest
class TeamProjectApplicationTests {

	@Autowired
	UserRepository Urepo;
	@Autowired
	YoutubeRepository youtubeRepository;

}
