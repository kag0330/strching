package com.stretching;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stretching.entity.emf.UniqueEntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@SpringBootApplication
public class TeamProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamProjectApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		UniqueEntityManagerFactory.emf = emf;
		
		//UniqueEntityManagerFactory.emf.close();
	}
}
