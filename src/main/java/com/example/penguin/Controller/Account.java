package com.example.penguin.Controller;

import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Entities.UserEntity;
import com.example.penguin.Service.ServiceImpl.OrderDetailServiceImpl;
import com.example.penguin.Service.ServiceImpl.OrderServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Account {

    @Autowired
    HttpSession session;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @GetMapping("/account")
    public String showAccount(Model model)
    {
        return getOnePage(model,1);
    }
    @GetMapping("/account/page/{pageNumber}")
    public String getOnePage(Model model , @PathVariable(name = "pageNumber")int pageNumber)
    {
        UserEntity account =  (UserEntity) session.getAttribute("account");

        int pageSize = 10;

        model.addAttribute("account",account);
        if(account!=null)
        {
            Page<OrderEntity> orderList = orderService.findPageByUser(pageNumber,pageSize,account.getId());
            int totalPage = orderList.getTotalPages();
            List<OrderEntity> orderEntityList = orderList.getContent();
            model.addAttribute("totalPage",totalPage);
            model.addAttribute("currentPage",pageNumber);
            model.addAttribute("orderList",orderEntityList);

        }
        return "account";

    }
}
