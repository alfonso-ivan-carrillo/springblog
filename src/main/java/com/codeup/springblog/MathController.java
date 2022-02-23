package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @RequestMapping(path ="/add/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String addNum(@PathVariable int num1, @PathVariable int num2){
        return "If you add " + num1 + " and " + num2 + " you get: " + (num1 + num2) + "!";
    }

    @RequestMapping(path = "/subtract/{num1}/from/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String subtractNum(@PathVariable int num1, @PathVariable int num2){
        return "If you submtract " + num1 + " from " + num2 + " you get: " + (num2 - num1) + "!";
    }

    @RequestMapping(path = "/multiply/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiplyNum(@PathVariable int num1, @PathVariable int num2){
        return "If you multiply " + num1 + " by " + num2 + "you'll get: " + (num1 * num2) + "!";
    }

    @GetMapping(path = "/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divideNum(@PathVariable int num1, @PathVariable int num2){
        return "if you divide " + num2 + " by " + num1 + " you'll get: " + (num1 / num2) + "!";
    }
}
