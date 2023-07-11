package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Entities.UserAccountEntity;
import com.example.penguin.Service.CategoryService;
import com.example.penguin.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductAminController {

    @Autowired
    ProductService productService;

    @Autowired
    HttpSession session;

    @Autowired
    CategoryService categoryService;
//    @GetMapping("/Admin_Product")
//    public String showProduct(Model model)
//    {
//        List<ProductEntity> allPro = productService.findAll();
//        model.addAttribute("allPro" , allPro);
//        return "/Admin_Product";
//    }

    @GetMapping("/Add_Product")
    public String addPro_Page(Model model)
    {
        List<CategoryEntity> listCategory = categoryService.findAll();
        model.addAttribute("listCategory",listCategory);
        return "Add_Product";
    }

    @PostMapping("/Add_Product/save")
    public String saveProduct(
                               @ModelAttribute(name = "name") String name  ,
                              @ModelAttribute(name ="description") String description,
                              @ModelAttribute(name="price") int price,
                              @ModelAttribute(name ="quantity") int quantity,
                              @ModelAttribute(name = "available") int availble,
                              @ModelAttribute(name = "category") int category,
                              RedirectAttributes rd

                              )
    {

        ProductEntity product = new ProductEntity();
        product.setProductName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setAvailable(availble);
        product.setCategory(categoryService.findById(category));

        productService.saveProduct(product);

        rd.addFlashAttribute("message" , "Đã thêm "+ product.getProductName() + "thành công");
        return "redirect:/Admin_Product";
    }

    @GetMapping("/Admin_Product/info_Product/{id}")
    public String showInforProdcut(@PathVariable(value = "id") Integer id,
                                   Model model
                                   )
    {

        ProductEntity product = productService.findById(id);
        model.addAttribute("product" , product);
        return "/info_Product";
    }

    @GetMapping("/Admin_Product")
    public String viewHomePage(Model model )
    {
        UserAccountEntity account =  (UserAccountEntity) session.getAttribute("account");


        return getOnePage(1,model) ;
    }

    @GetMapping("/Admin_Product/page/{pageNumber}")
    public String getOnePage(@PathVariable(value = "pageNumber") int pageNumber , Model model)
    {



//        UserAccountEntity account =  (UserAccountEntity) session.getAttribute("account");
//        if(account== null || account.getRole()!=1)
//        {
//            return "redirect:/" ;
//        }
        int pageSize = 10;
        Page<ProductEntity> productEntitiesPage = productService.findPage(pageNumber,pageSize);

        int totalPage = productEntitiesPage.getTotalPages();
        List<ProductEntity> productEntities = productEntitiesPage.getContent();



        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("products",productEntities);

        return "Admin_Product";
    }




    @GetMapping("/Admin_Product/Delete/{id}")
    public String Delete(@PathVariable(value = "id") int id , RedirectAttributes rd)
    {

        this.productService.deleteById(id);
        rd.addFlashAttribute("message","đã xóa thành công");
        return "redirect:/Admin_product";

    }

    @GetMapping("/Admin_Product/edit_InfoProduct/{id}")
    public String showPageEdit(@PathVariable(value = "id")int id , Model model)
    {

        List<CategoryEntity> listCategory = categoryService.findAll();

        ProductEntity product = productService.findById(id);
        CategoryEntity category = product.getCategory();

        // danh mục hiện tại
        model.addAttribute("itemCategory",category);
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("product" , product);
        return "/edit_InfoProduct";
    }
    @PostMapping("/edit_InfoProduct/update/{id}")
    public String editProduct(@PathVariable(value = "id") int id,
                              @ModelAttribute(name = "name") String name ,
                              @ModelAttribute(name ="description") String description,
                              @ModelAttribute(name="price") int price,
                              @ModelAttribute(name ="quantity") int quantity,
                              @ModelAttribute(name = "available") int availble,
                              @ModelAttribute(name = "category") int category,
                              RedirectAttributes rd)
    {

        ProductEntity product = productService.findById(id);

        product.setProductName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setAvailable(availble);
        product.setCategory(categoryService.findById(category));

        productService.saveProduct(product);

        rd.addFlashAttribute("message","Đã lưu thay đổi thành công");
        return "redirect:/Admin_Product";
    }


}
