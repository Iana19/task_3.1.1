package com.task_springboot.demo.controller;

import com.task_springboot.demo.dao.RoleDao;
import com.task_springboot.demo.model.User;
import com.task_springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleDao roleDao;

    @Autowired
    public AdminController(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("/admin")
    public ModelAndView allUsers() {
        List<User> users = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("usersList", users);
        return modelAndView;
    }

    @GetMapping(value = "/admin/add")
    public String addUser() {
        return "addUser";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminEditUser");
        modelAndView.addObject("user", user);
        modelAndView.addObject("rolelist", roleDao.getAllRoles());
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute("id") Long id, @ModelAttribute("name") String name,
            @ModelAttribute("password") String password, @ModelAttribute("lastname") String lastname,
            @ModelAttribute("age") byte age, @RequestParam("roles") String[] roles, String login)
    {
        userService.implEditUser(id, name, lastname, age, password, roles, login);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }

}