package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Service.ServiceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderAdminController {

    @Autowired
    OrderServiceImpl orderServiceImpl;
    @GetMapping("/Admin_Order")
    public String showOrder(Model model)
    {

//        List<OrderEntity> orderList  = orderService.findAll();
//
//        model.addAttribute("orderList" ,orderList);

        return getOnePage(1,model);
    }

    @GetMapping("/Admin_Order/page/{pageNumber}")
    public String getOnePage(@PathVariable(value = "pageNumber") int pageNumber , Model model)
    {

        int pageSize = 15;
        Page<OrderEntity> orderEntityPage = orderServiceImpl.findPage(pageNumber,pageSize);

        int totalPage = orderEntityPage.getTotalPages();
        List<OrderEntity> orderEntityList = orderEntityPage.getContent();

        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("orders",orderEntityList);
        return "Admin_Order";

    }








}
