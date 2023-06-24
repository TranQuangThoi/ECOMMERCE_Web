package com.example.penguin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class loginController {


    @GetMapping("/login")
    private String login()
    {
        return "login";
    }
}
