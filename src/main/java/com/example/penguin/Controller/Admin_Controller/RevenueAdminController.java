package com.example.penguin.Controller.Admin_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RevenueAdminController {

    @GetMapping("/Admin_Revenue")
    public String showRevenue()
    {
            return "/Admin_Revenue";
    }
}
