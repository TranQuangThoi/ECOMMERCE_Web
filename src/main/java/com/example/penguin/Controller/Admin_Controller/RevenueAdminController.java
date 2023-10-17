package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Service.ServiceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RevenueAdminController {

    @Autowired
    OrderServiceImpl orderServiceImpl;
    @GetMapping("/Admin_Revenue")
    public String showRevenue(Model model)
    {
        List<OrderEntity> orderSold = orderServiceImpl.findOrderSold();

        model.addAttribute("orderSold",orderSold);
            return "/Admin_Revenue";
    }
    @PostMapping("/findOrderOfDate")
    public String findOrderByDate(Model model , @ModelAttribute("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
                                  @ModelAttribute("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate ,
                                  RedirectAttributes rd
    )
    {
        List<OrderEntity> orderEntityList = orderServiceImpl.findOrderOfDate(startDate,endDate);
        if (orderEntityList.isEmpty())
        {
            rd.addFlashAttribute("error","Không có đơn hàng nào trong thời gian này");
        }
        model.addAttribute("orderList",orderEntityList);
        System.out.println(startDate +" và"+ endDate);
        return "Admin_Revenue";
    }


}
