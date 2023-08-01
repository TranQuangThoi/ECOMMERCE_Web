package com.example.penguin.Controller;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Entities.ImagesEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Service.CategoryService;
import com.example.penguin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {


    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/shop")
    public String showPageShop(Model model)
    {
        List<ProductEntity> productList = productService.findAll();
        List<CategoryEntity> categoryList = categoryService.findAll();

        model.addAttribute("categoryList",categoryList);
        model.addAttribute("productList",productList);
        return "shop";
    }




}
