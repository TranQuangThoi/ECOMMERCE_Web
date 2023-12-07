package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Service.UserAccService;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import com.example.penguin.Entities.UserEntity;
import com.example.penguin.Service.ServiceImpl.UserAccServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;



@Controller
public class AccAdminController {

    @Autowired
    private UserAccService userAccServiceImpl;

    @GetMapping("Admin_User")
    public String showUser(Model model)
    {

        return getOnePage(model, 1);
    }
    @GetMapping("/user/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable(name = "pageNumber") int pageNumber) {

        int pageSize = 6;

        Page<UserEntity> userList = userAccServiceImpl.findAllUser(pageNumber, pageSize);
        int totalPage = userList.getTotalPages();
        List<UserEntity> userEntities = userList.getContent();
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("userAcc", userList);

        return "Admin_User";

    }

    @GetMapping("DeleteUser/{id}")
    public String deleteUser( @PathVariable(value = "id") int id , RedirectAttributes rd )
    {

        Optional<UserEntity> userAccount = userAccServiceImpl.findById(id);
        UserEntity userAccount1 = userAccount.get();

        String name =userAccount1.getName();
        this.userAccServiceImpl.deleteById(id);
        rd.addFlashAttribute("mesage" ,"Đã xóa " + name +" thành công");
        return "redirect:/Admin_User";
    }



}
