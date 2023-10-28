package com.stretching;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stretching.entity.User;
import com.stretching.repository.UserRepository;

@SpringBootTest
class TeamProjectApplicationTests {

	@Autowired
	UserRepository Urepo;
	
	@Test
	public void testSelectAll() {
		List<User> Ulists = Urepo.findAll();

		for (User Ulist : Ulists) {
			System.out.println(Ulist.toString());
		}
	}

}
