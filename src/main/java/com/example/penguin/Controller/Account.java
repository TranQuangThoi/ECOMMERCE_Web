package com.example.penguin.Controller;

import com.example.penguin.Entities.UserAccountEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Account {

    @Autowired
    HttpSession session;
    @GetMapping("/account")
    public String showPageAccount(Model model)
    {

        UserAccountEntity account =  (UserAccountEntity) session.getAttribute("account");

        model.addAttribute("account",account);
        return "account";

    }
}
