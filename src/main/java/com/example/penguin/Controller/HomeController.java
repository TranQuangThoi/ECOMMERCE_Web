package com.example.penguin.Controller;

import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/shop")
    public String showPageShop(Model model)
    {

        List<ProductEntity> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "shop";
    }

    @GetMapping("/about")
    public String showPageAbout()
    {
        return "about";
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

    @RequestMapping("/*") // Định nghĩa một mapping chung cho tất cả các URL không xác định
    public String handleUnknownPage() {
        return "error"; // Trả về trang lỗi hoặc trang mặc định khác
    }



}
