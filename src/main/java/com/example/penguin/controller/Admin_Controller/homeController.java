package com.example.penguin.controller.Admin_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/")
    private String home(){
        return "/Admin_home" ;
    }

//    @GetMapping("/home")
//    private String Admin_home(){
//        return "/Admin_home";
//    }



}
