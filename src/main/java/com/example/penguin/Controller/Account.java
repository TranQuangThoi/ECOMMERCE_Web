package com.example.penguin.Controller;

import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Entities.UserEntity;
import com.example.penguin.Repository.UserAccRepositoy;
import com.example.penguin.Service.OrderDetailService;
import com.example.penguin.Service.OrderService;
import com.example.penguin.Service.ServiceImpl.OrderDetailServiceImpl;
import com.example.penguin.Service.ServiceImpl.OrderServiceImpl;
import com.example.penguin.Service.UserAccService;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class Account {

    @Autowired
    HttpSession session;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private UserAccService userAccService;

    @GetMapping("/account")
    public String showAccount(Model model)
    {
        return getOnePage(model,1);
    }
    @GetMapping("/account/page/{pageNumber}")
    public String getOnePage(Model model , @PathVariable(name = "pageNumber")int pageNumber)
    {
        UserEntity account =  (UserEntity) session.getAttribute("account");

        int pageSize = 9;

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
    @PostMapping("/account/update")
    public String updateAccount(RedirectAttributes rd  , @RequestParam("name")String name,
                                @RequestParam("address")String address,
                                @RequestParam("birthday") String birthday,
                                @RequestParam("phone")String phone,
                                @RequestParam(value = "email",required = false)String email,
                                @RequestParam(value = "oldPassword",required = false)String oldPass,
                                @RequestParam(value = "newPassword",required = false)String newPassword)
    {
        UserEntity account =  (UserEntity) session.getAttribute("account");

            if (StringUtils.isNoneBlank(oldPass) && StringUtils.isNoneBlank(newPassword))
            {
                String decodePass = new String(Base64.getDecoder().decode(account.getPassword()));
                if (decodePass.equals(oldPass.trim()))
                {
                    String password = Base64.getEncoder().encodeToString(newPassword.trim().getBytes());
                    account.setPassword(password);
                }else {
                    rd.addFlashAttribute("error", "Mật khẩu không đúng !!!");
                    return "redirect:/account";
                  }
            }
            account.setName(name);
            account.setEmail(email);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(birthday);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            account.setBirthDate(new java.sql.Date(parsedDate.getTime()));
            account.setAddress(address);
            account.setPhone(phone);
            userAccService.saveUser(account);

        session.setAttribute("account", account);

        return "redirect:/account";
    }
}
