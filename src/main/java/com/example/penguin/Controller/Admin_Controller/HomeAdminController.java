package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.UserAccountEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAdminController {

    @Autowired
    HttpSession session;

    @GetMapping("/Admin_Home")
    private String showManage(Model model){

        return "/Admin_home" ;
    }





}
