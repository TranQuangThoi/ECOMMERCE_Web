package com.example.penguin.Controller.Admin_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderAdminController {
    @GetMapping("/Admin_Order")
    public String showOrder()
    {
        return "/Admin_Order";
    }

}
