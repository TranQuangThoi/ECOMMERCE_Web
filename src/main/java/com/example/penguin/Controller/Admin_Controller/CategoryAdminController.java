package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Service.CategoryService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class CategoryAdminController {

    @Autowired
    CategoryService categoryService;
    @GetMapping("/Admin_Category")
    public String showCategory(Model model)
    {
        List<CategoryEntity> listAll = categoryService.findAll();
        model.addAttribute("allCategory" ,listAll);

        return "/Admin_Category";
    }


    @GetMapping("/Delete/{id}")
    public String DeleteCate(RedirectAttributes rd , @PathVariable(value = "id") int id )
    {

        Optional<CategoryEntity> category = categoryService.findById(id);

        CategoryEntity categoryEntity = category.get();

        this.categoryService.deleteById(id);

        rd.addFlashAttribute("mesage" ,"Đã xóa " +categoryEntity.getNameCategory()  +" thành công");
        return "redirect:/Admin_Category";

    }


    @GetMapping("/Add_Category")
    public String addCategory(Model model)
    {
        return "/Add_Category";
    }

    @PostMapping("/Add_Category/save")
    public String saveCategory(CategoryEntity categoryEntity ,
            @ModelAttribute(name="name") String name ,
            @ModelAttribute(name = "status") int status ,
             RedirectAttributes rd)
    {

        categoryEntity.setNameCategory(name);
        categoryEntity.setStatus(status);
        categoryService.saveCategory(categoryEntity);
        rd.addFlashAttribute("mesage","Đã thêm danh mục mới thành công");

        return "redirect:/Admin_Category";

    }
}
