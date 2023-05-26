package com.example.backend.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.backend.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {

}
