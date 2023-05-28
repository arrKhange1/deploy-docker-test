package com.example.backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.User;
import com.example.backend.repo.UserRepo;


@RestController
public class HelloController {
	
	private static Long counter = 0L;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello, world!";
	}
	
	@GetMapping("/user/all")
	public ResponseEntity<?> getUser() {
		User user = new User();
		user.setName(UUID.randomUUID().toString());
		userRepo.save(user);
		return ResponseEntity.ok(userRepo.findAll());
	}
	
	@GetMapping("/compose-test")
	public String composeTest() {
		return "ci/cd is working with docker compose!";
	}
	
	@GetMapping("/delay")
	public Long delay() throws InterruptedException {
		Thread.sleep(50);
		counter++;
		return 0L;
	}
	
	@GetMapping("/reset-counter")
	public ResponseEntity<?> reset() {
		counter = 0L;
		return ResponseEntity.ok("ok");
	}
	
	@GetMapping("/counter")
	public Long counter() {
		return counter;
	}

}
