package com.example.backend.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.User;
import com.example.backend.repo.UserRepo;
import com.example.backend.service.AsyncTaskService;


@RestController
public class HelloController {
	
	@Autowired
    private AsyncTaskService asyncTaskService;
	
	public static Long counter = 0L;
	
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
	public CompletableFuture<Long> delay() throws InterruptedException {
		return asyncTaskService.doWork();
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
