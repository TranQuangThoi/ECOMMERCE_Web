package com.example.penguin.Controller;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Service.CategoryService;
import com.example.penguin.Service.ProductService;
import com.example.penguin.Service.ServiceImpl.CategoryServiceImpl;
import com.example.penguin.Service.ServiceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {


    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private CategoryService categoryServiceImpl;

    @GetMapping("/shop")
    public String showPageShop(Model model) {
        List<ProductEntity> productList = productServiceImpl.findAll();
        List<CategoryEntity> categoryList = categoryServiceImpl.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList", productList);
        return "shop";
    }


}
