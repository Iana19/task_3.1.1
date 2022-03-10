package com.task_springboot.demo.service;

import com.task_springboot.demo.dao.RoleDao;
import com.task_springboot.demo.dao.UserDao;
import com.task_springboot.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public void save(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.getUserByName(username);
        return user;
    }
}
