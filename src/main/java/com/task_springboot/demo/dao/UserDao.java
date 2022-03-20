package com.task_springboot.demo.dao;

import com.task_springboot.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();
    void save(User user);
    void delete(User user);
    User getById(Long id);
    User getUserByLogin(String login);
}
