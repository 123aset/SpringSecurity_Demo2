package com.example.demo.service;

import com.example.demo.model.Users;

import java.util.List;

public interface UserService {
    List<Users> getAll();

    Users getOne(String email);

    void add(Users user);
}
