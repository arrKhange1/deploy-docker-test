package com.example.backend.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.backend.controller.HelloController;

@Service
public class AsyncTaskService {

    @Async
    public CompletableFuture<Long> handleExampleRequestAsync() throws InterruptedException {
        // Perform some logic here
        Thread.sleep(1000);
        HelloController.counter++;
        return CompletableFuture.completedFuture(0L);
    }
}