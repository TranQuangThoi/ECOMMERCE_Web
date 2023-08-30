package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Service.ServiceImpl.CategoryServiceImpl;
import com.example.penguin.Service.ServiceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class CategoryAdminController {

    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    CategoryServiceImpl categoryServiceImpl;
    @GetMapping("/Admin_Category")
    public String showCategory(Model model)
    {
        List<CategoryEntity> listAll = categoryServiceImpl.findAll();
        model.addAttribute("allCategory" ,listAll);

        for (CategoryEntity itemCate : listAll) {
            List<ProductEntity> product = productServiceImpl.findProByCategoryId(itemCate.getIdCategory());
            long count = product.size(); // Đếm số lượng sản phẩm
            itemCate.setProductCount(count); // Gán số lượng sản phẩm vào thuộc tính productCount

        }


        return "/Admin_Category";
    }


    @GetMapping("/Admin_Category/Delete/{id}")
    public String DeleteCate(RedirectAttributes rd , @PathVariable(value = "id") int id )
    {


        CategoryEntity category = categoryServiceImpl.findById(id);

        List<ProductEntity> listPro = productServiceImpl.findProByCategoryId(id);

        if(listPro != null)
        {
            for(ProductEntity itemPro : listPro)
            {
//                productService.delete(itemPro);
                productServiceImpl.deleteProById(itemPro.getIdProduct());
            }

            this.categoryServiceImpl.deleteById(id);
        }else {
            this.categoryServiceImpl.deleteById(id);
        }



        this.categoryServiceImpl.deleteById(id);

        rd.addFlashAttribute("message" ,"Đã xóa " +category.getNameCategory()  +" thành công");
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
        categoryServiceImpl.saveCategory(categoryEntity);
        rd.addFlashAttribute("message","Đã thêm danh mục mới thành công");

        return "redirect:/Admin_Category";

    }

    @GetMapping("/Admin_Category/edit_InfoCate/{id}")
    public String showEditCate(@PathVariable(value = "id")int id , Model model)
    {

        CategoryEntity category = categoryServiceImpl.findById(id);
        model.addAttribute("category",category);

        return "/edit_InfoCate";
    }

    @PostMapping("/Admin_Category/update/{id}")
    public  String updateCate(@PathVariable(value = "id") int id ,
                              @ModelAttribute(name = "name") String namecate,
                              @ModelAttribute(name = "status") int status,
                              RedirectAttributes rd,
                              Model model
                              )
    {

        CategoryEntity category = categoryServiceImpl.findById(id);

        category.setNameCategory(namecate);
        category.setStatus(status);

        categoryServiceImpl.saveCategory(category);



        rd.addFlashAttribute("message","Đã chuyển sang "+category.getNameCategory() +" thành công");
        return "redirect:/Admin_Category";


    }
}
