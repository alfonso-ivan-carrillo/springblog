package com.codeup.springblog.controller;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model){
        List<Post> allPosts = new ArrayList<>();
        Post postTwo = new Post(2, "second", "news of the day");
        Post postThree = new Post(3, "third", "old news of the day");

        allPosts.add(postTwo);
        allPosts.add(postThree);
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model){
        Post postOne = new Post(1, "New", "Russia vs Ukraine");
        model.addAttribute("singlePost", postOne);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createGet(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submitCreateForm(@RequestParam(name="title") String title, @RequestParam(name = "body") String body){
        Post newPost = new Post(title, body);
        postsDao.save(newPost);

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model mode){
        Post postToEdit = postsDao.getById(id);
        mode.addAttribute("postToEdit", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitEdit(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @PathVariable long id){
        Post postToEdit = postsDao.getById(id);
        postToEdit.setTitle(title);
        postToEdit.setBody(body);
        postsDao.save(postToEdit);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }



}
