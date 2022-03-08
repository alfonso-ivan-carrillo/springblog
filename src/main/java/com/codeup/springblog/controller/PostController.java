package com.codeup.springblog.controller;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private PostRepository postsDao;
    private UserRepository userDao;
    private final EmailService emailService;


    public PostController(PostRepository postsDao, UserRepository userDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model){
        model.addAttribute("allPosts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model){
        model.addAttribute("singlePost", postsDao.getById(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createGet(Model model){
        model.addAttribute("newPost", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submitCreateForm(@ModelAttribute Post newPost){
//            (@RequestParam(name="title") String title, @RequestParam(name = "body") String body){
//        newPost.setUser(userDao.getById(1L));
        newPost.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        emailService.prepareAndSend(newPost, "New post created", "You had posted to our blog!");
        postsDao.save(newPost);


//        Post newPost = new Post(title, body);
//        newPost.setUser(userDao.getById(1L));
//        postsDao.save(newPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model){
        Post postToEdit = postsDao.getById(id);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (postToEdit.getUser().getId() == loggedInUser.getId()){
            model.addAttribute("postToEdit", postToEdit);
            return "posts/edit";
        } else {
            return "redirect:/posts";
        }

    }

    @PostMapping("/posts/{id}/edit")
    public String submitEdit(@ModelAttribute Post postToEdit, @PathVariable long id){
        if (postsDao.getById(id).getUser().getId() == ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()) {
            postToEdit.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            // grab the post from our DAO
//    Post postToEdit = postsDao.getById(id);
            // use setters to set new values to the object
//    postToEdit.setTitle(title);
//    postToEdit.setBody(body);
            // save the object with new values
            postsDao.save(postToEdit);
        }

        return "redirect:/posts";
//    public String submitEdit(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @PathVariable long id){
//
//        Post postToEdit = postsDao.getById(id);
//        postToEdit.setTitle(title);
//        postToEdit.setBody(body);
//        postsDao.save(postToEdit);
//        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/send-email")
    public String sendEmail(){
        emailService.prepareAndSend("testing", "succesfull?");
        return "redirect:/";
    }



}
