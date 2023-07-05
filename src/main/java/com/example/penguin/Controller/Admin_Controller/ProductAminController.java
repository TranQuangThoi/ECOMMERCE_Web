package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Service.CategoryService;
import com.example.penguin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.util.List;

@Controller
public class ProductAminController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    @GetMapping("/Admin_Product")
    public String showProduct(Model model)
    {

        List<ProductEntity> allPro = productService.findAll();
        model.addAttribute("allPro" , allPro);
        return "/Admin_Product";
    }

    @GetMapping("/Add_Product")
    public String addPro_Page(Model model)
    {
        List<CategoryEntity> listCategory = categoryService.findAll();
        model.addAttribute("listCategory",listCategory);

        return "/Add_Product";
    }

    @PostMapping("/Add_Product/save")
    public String saveProduct(@ModelAttribute(name = "name") String name  ,
                              @ModelAttribute(name ="description") String description,
                              @ModelAttribute(name ="discount") int discount,
                              @ModelAttribute(name="price") BigInteger price,
                              @ModelAttribute(name ="quantity") int quantity,
                              @ModelAttribute(name = "available") int availble,
                              @ModelAttribute(name = "category") int category,
                              RedirectAttributes rd ,
                              ProductEntity product,
                              Model model
                              )
    {
        product.setProductName(name);
        product.setDescription(description);
        product.setDiscount(discount);
        product.setQuantity(quantity);
        product.setAvailable(availble);
//        product.setCategory(categoryService.findById(category));

        productService.saveProduct(product);

        rd.addFlashAttribute("message" , "Đã thêm "+ name + "thành công");
        return "Admin_Product";
    }
}
