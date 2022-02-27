package com.codeup.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String index(){
        return "<h1>Posts index page</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(){
        return "<h1>View an individual post</h1>";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createGet(){
        return "<h1>View the form for creating a post</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "<h1>Create a new post</h1>";
    }

}
