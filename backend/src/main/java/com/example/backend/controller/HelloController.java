package com.example.backend.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.User;
import com.example.backend.repo.UserRepo;
import com.example.backend.service.AsyncTaskService;


@RestController
@EnableAsync
public class HelloController {
	
	@Autowired
    private AsyncTaskService asyncTaskService;
	
	public static AtomicLong counter = new AtomicLong(0);
	
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
	public ResponseEntity<Integer> delay() throws InterruptedException {
		//asyncTaskService.doWork();
		Runnable runnable = () -> {
			for (int i = 0; i < 100000; i++) {
				counter.incrementAndGet();
			}
		};
		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}
		return ResponseEntity.ok(0);
	}
	
	@GetMapping("/reset-counter")
	public ResponseEntity<?> reset() {
		counter = new AtomicLong(0);
		return ResponseEntity.ok("ok");
	}
	
	@GetMapping("/counter")
	public AtomicLong counter() {
		return counter;
	}

}
