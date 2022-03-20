package com.task_springboot.demo.service;

import com.task_springboot.demo.dao.RoleDao;
import com.task_springboot.demo.dao.UserDao;
import com.task_springboot.demo.model.Role;
import com.task_springboot.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public User implEditUser(Long id, String name, String lastname, byte age, String password, String[] roles, String login) {

        User user = userDao.getById(id);
        user.setName(name);
        user.setLastname(lastname);
        user.setAge(age);
        //22.03
        user.setLogin(login);
        if (!password.isEmpty()) {
            user.setPassword(password);
        }
        Set<Role> setRoles = new HashSet<>();
        for (String st : roles) {
            if (st.equals("ADMIN")) {
                Role roleOfAdmin = roleDao.createRoleIfNotFound("ADMIN", 1L);
                setRoles.add(roleOfAdmin);
            }
            if (st.equals("USER")) {
                Role roleOfUser = roleDao.createRoleIfNotFound("USER", 2L);
                setRoles.add(roleOfUser);
            }
        }
        user.setRoles(setRoles);
        userDao.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userDao.getUserByLogin(login);
        return user;
    }

}