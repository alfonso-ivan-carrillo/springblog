package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String landingPage(){
        return "welcome";    // references html page - welcome
    }

    @GetMapping("/quote-of-the-day/by/{author}")
    public String quote(@PathVariable String author, Model model){
        model.addAttribute("author", author);
        return "quotes";   // references html page - quotes
    }

}
