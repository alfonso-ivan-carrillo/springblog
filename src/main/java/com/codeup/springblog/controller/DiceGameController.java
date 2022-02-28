package com.codeup.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiceGameController {

    @GetMapping("/roll-dice")
    public String rollDiceForm(){
        return "diceGame";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model){
        int randomNumber = (int) (Math.floor(Math.random() * 6 - 1) + 1);
        System.out.println(randomNumber);
        System.out.println(n);
        if ( randomNumber == n){
            model.addAttribute("result", "You Win!");

        } else {
            model.addAttribute("result", "You lose!");
        }
        return "diceGame";
    }
}
