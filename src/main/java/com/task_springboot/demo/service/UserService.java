package com.task_springboot.demo.service;

import com.task_springboot.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void save(User user);
    void delete(User user);
    User getById(Long id);
}
