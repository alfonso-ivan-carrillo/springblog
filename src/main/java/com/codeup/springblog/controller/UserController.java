//package com.codeup.springblog.controller;
//
//import com.codeup.springblog.repositories.UserRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//public class UserController {
//    private UserRepository userDao;
//
//    public UserController(UserRepository userDao){
//        this.userDao = userDao;
//    }
//
//    @GetMapping("users/userProfile/{id}")
//    public String profileIndex(@PathVariable long id, Model model){
//        model.addAttribute("profileIndex", userDao.getById(id));
//        return "users/userProfile";
//    }
//
//    @GetMapping("users/create")
//    public String showCreateForm(){
//        return "users/usersCreate";
//    }
//
//
//
//}
