package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Entities.ImagesEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Entities.UserEntity;
import com.example.penguin.Service.CartDetailService;
import com.example.penguin.Service.ImageService;
import com.example.penguin.Service.ProductService;
import com.example.penguin.Service.ServiceImpl.CategoryServiceImpl;
import com.example.penguin.Service.ServiceImpl.CloudinaryService;
import com.example.penguin.Service.ServiceImpl.ImageServiceImpl;
import com.example.penguin.Service.ServiceImpl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductAminController {

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private ImageService imageServiceImpl;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    HttpSession session;
    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;
//    @GetMapping("/Admin_Product")
//    public String showProduct(Model model)
//    {
//        List<ProductEntity> allPro = productService.findAll();
//        model.addAttribute("allPro" , allPro);
//        return "/Admin_Product";
//    }

    //-------------------------ADMIN----------------------------------\\

    @GetMapping("/Add_Product")
    public String addPro_Page(Model model) {
        List<CategoryEntity> listCategory = categoryServiceImpl.findAll();
        model.addAttribute("listCategory", listCategory);
        return "Add_Product";
    }

    @PostMapping("/Add_Product/save")
    public String saveProduct(
            @ModelAttribute(name = "name") String name,
            @ModelAttribute(name = "description") String description,
            @ModelAttribute(name = "price") int price,
            @ModelAttribute(name = "quantity") int quantity,
            @ModelAttribute(name = "available") int availble,
            @ModelAttribute(name = "category") int category,
            @ModelAttribute(name = "image") MultipartFile[] image,
            RedirectAttributes rd

    ) {


        ProductEntity product = new ProductEntity();
        product.setProductName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setAvailable(availble);
        product.setPrice(price);
        product.setCategory(categoryServiceImpl.findById(category));
        productServiceImpl.saveProduct(product);

        for (MultipartFile itemmage : image) {
            if (!itemmage.isEmpty()) {
                String urlImage = cloudinaryService.uploadFile(itemmage);
                ImagesEntity imagesEntity = new ImagesEntity();
                imagesEntity.setUrl(urlImage);
                imagesEntity.setProduct(product);
                imageServiceImpl.saveImage(imagesEntity);
            }


        }

        rd.addFlashAttribute("message", "Đã thêm " + product.getProductName() + "thành công");
        return "redirect:/Admin_Product";
    }

    @GetMapping("/Admin_Product/info_Product/{id}")
    public String showInforProdcut(@PathVariable(value = "id") Integer id,
                                   Model model
    ) {

        ProductEntity product = productServiceImpl.findById(id);

        List<ImagesEntity> listImage = imageServiceImpl.findByIdPro(id);

        model.addAttribute("listImage", listImage);
        model.addAttribute("product", product);
        return "/info_Product";
    }

    @GetMapping("/Admin_Product")
    public String viewHomePage(Model model) {
        UserEntity account = (UserEntity) session.getAttribute("account");


        return getOnePage(1, model);
    }

    @GetMapping("/Admin_Product/page/{pageNumber}")
    public String getOnePage(@PathVariable(value = "pageNumber") int pageNumber, Model model) {


//        UserAccountEntity account =  (UserAccountEntity) session.getAttribute("account");
//        if(account== null || account.getRole()!=1)
//        {
//            return "redirect:/" ;
//        }
        int pageSize = 10;
        Page<ProductEntity> productEntitiesPage = productServiceImpl.findPage(pageNumber, pageSize);

        int totalPage = productEntitiesPage.getTotalPages();
        List<ProductEntity> productEntities = productEntitiesPage.getContent();


        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("products", productEntities);

        return "Admin_Product";
    }


    @GetMapping("/Admin_Product/Delete/{id}")
    public String Delete(@PathVariable(value = "id") int id, RedirectAttributes rd) {
        ProductEntity product = productServiceImpl.findById(id);

        rd.addFlashAttribute("message", "đã xóa sản phẩm " + product.getProductName() + "thành công");
        cartDetailService.deleteAllByProduct(product);
        productServiceImpl.deleteProById(id);

        return "redirect:/Admin_Product";

    }

    @GetMapping("/Admin_Product/edit_InfoProduct/{id}")
    public String showPageEdit(@PathVariable(value = "id") int id, Model model) {

        List<CategoryEntity> categoryEntityList = categoryServiceImpl.findAll();
        List<ImagesEntity> imagesEntityList = imageServiceImpl.findByIdPro(id);

        ProductEntity product = productServiceImpl.findById(id);
        CategoryEntity category = product.getCategory();


        // danh mục hiện tại
        model.addAttribute("itemCategory", category);
        model.addAttribute("listCategory", categoryEntityList);
        model.addAttribute("product", product);
        model.addAttribute("listImage", imagesEntityList);
        return "/edit_InfoProduct";
    }

    @PostMapping("/edit_InfoProduct/update/{id}")
    public String editProduct(@PathVariable(value = "id") int id,
                              @ModelAttribute(name = "name") String name,
                              @ModelAttribute(name = "description") String description,
                              @ModelAttribute(name = "price") int price,
                              @ModelAttribute(name = "quantity") int quantity,
                              @ModelAttribute(name = "available") int availble,
                              @ModelAttribute(name = "category") int category,
                              @ModelAttribute(name = "image") MultipartFile[] image,
                              RedirectAttributes rd) {

        ProductEntity product = productServiceImpl.findById(id);

        product.setProductName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setAvailable(availble);
        product.setCategory(categoryServiceImpl.findById(category));
        productServiceImpl.saveProduct(product);

        List<ImagesEntity> imagesEntityList = imageServiceImpl.findByIdPro(id);

//        thay đổi ảnh theo anh goc
        for (int i = 0; i < imagesEntityList.size(); i++) {
            ImagesEntity imagesEntity = imagesEntityList.get(i);
            MultipartFile imageM = image[i];

            // Kiểm tra ảnh có được chọn thay đổi hay không
            if (imageM != null && !imageM.isEmpty()) {
                String url = cloudinaryService.uploadFile(imageM);
                imagesEntity.setUrl(url);
                imageServiceImpl.saveImage(imagesEntity);
            }
        }

//        productService.deleteImageByPro(id);
        // theem anh
        if (imagesEntityList.size() < image.length) {
            for (MultipartFile itemImage : image) {
                if (!itemImage.isEmpty()) {
                    String url = cloudinaryService.uploadFile(itemImage);
                    ImagesEntity imagesEntity = new ImagesEntity();
                    imagesEntity.setUrl(url);
                    imagesEntity.setProduct(product);
                    imageServiceImpl.saveImage(imagesEntity);
                }
            }
        }


        rd.addFlashAttribute("message", "Đã lưu thay đổi thành công");
        return "redirect:/Admin_Product";
    }


    //------------------------------------USER------------------------------------\\

}
