package com.example.backend.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.example.backend.controller.HelloController;

@Service
public class AsyncTaskService {
    
    @Async
    public void doWork() throws InterruptedException {
    //	Thread.sleep(100);
    	HelloController.counter.incrementAndGet();
    }
    
}