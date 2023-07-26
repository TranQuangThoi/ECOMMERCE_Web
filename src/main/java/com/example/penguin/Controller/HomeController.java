package com.example.penguin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/shop")
    public String showPageShop()
    {
        return "shop";
    }

    @GetMapping("/about")
    public String showPageAbout()
    {
        return "about";
    }

    @GetMapping("/account")
    public String showPageAccount()
    {
        return "account";
    }
    @GetMapping("/blog")
    public String showPageBlog()
    {
        return "blog";
    }

    @GetMapping("/cart")
    public String showPageCart()
    {
        return "cart";
    }

    @GetMapping("/contact")
    public String showContact()
    {
        return "contact";
    }

    @GetMapping("/sproduct")
    public String showPageProduct()
    {
        return "sproduct";
    }


}
