package com.example.penguin.Controller;

import com.example.penguin.Entities.ImagesEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Service.ImageService;
import com.example.penguin.Service.ProductService;
import com.example.penguin.Service.ServiceImpl.ImageServiceImpl;
import com.example.penguin.Service.ServiceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productServiceImpl;
    @Autowired
    private ImageService imageServiceImpl;

    @GetMapping("/")
    public String showHomePage(Model model) {
        Pageable pageable = PageRequest.of(0,10);
        List<ProductEntity> productEntities = productServiceImpl.findTop10(pageable);
        model.addAttribute("top10",productEntities);
        return "home";
    }


    @GetMapping("/about")
    public String showPageAbout() {
        return "about";
    }


    @GetMapping("/blog")
    public String showPageBlog() {
        return "blog";
    }


    @GetMapping("/contact")
    public String showContact() {
        return "contact";
    }

    @GetMapping("/sproduct/{id}")
    public String showPageProduct(Model model, @PathVariable(name = "id") int id) {

        List<ImagesEntity> imagesList = imageServiceImpl.findByIdPro(id);
        ProductEntity product = productServiceImpl.findById(id);
        Pageable pageable = PageRequest.of(0, 4);

        List<ProductEntity> productEntities = productServiceImpl.findRelateProduce(product.getCategory().getIdCategory(), pageable);

        model.addAttribute("imagesList", imagesList);
        model.addAttribute("product", product);
        model.addAttribute("productRelate", productEntities);
        return "sproduct";
    }

    @RequestMapping("/*") // Định nghĩa một mapping chung cho tất cả các URL không xác định
    public String handleUnknownPage() {
        return "error"; // Trả về trang lỗi hoặc trang mặc định khác
    }


}
