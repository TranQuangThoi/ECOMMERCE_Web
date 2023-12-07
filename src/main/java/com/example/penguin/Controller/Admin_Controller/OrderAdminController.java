package com.example.penguin.Controller.Admin_Controller;

import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Service.OrderService;
import com.example.penguin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderAdminController {

    @Autowired
    private OrderService orderServiceImpl;
    @Autowired
    private ProductService productService;

    @GetMapping("/Admin_Order")
    public String showOrder(Model model) {
        return getOnePage(1, model);
    }

    @GetMapping("/Admin_Order/page/{pageNumber}")
    public String getOnePage(@PathVariable(value = "pageNumber") int pageNumber, Model model) {

        int pageSize = 12;
        Page<OrderEntity> orderEntityPage = orderServiceImpl.findPage(pageNumber, pageSize);

        int totalPage = orderEntityPage.getTotalPages();
        List<OrderEntity> orderEntityList = orderEntityPage.getContent();

        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("orders", orderEntityList);
        return "Admin_Order";

    }

    @PostMapping("/Admin_Order/{id}")
    public String updateStatusOrder(@PathVariable int id,@RequestParam("action") String action)
    {
        OrderEntity order = orderServiceImpl.findById(id);
        if (order.getSatus()==4 || order.getSatus()==3)
        {
            return "redirect:/Admin_Order";
        }
        switch (action) {
            case "confirmed":
                order.setSatus(1);
                break;
            case "ship":
                order.setSatus(2);
                break;
            case "delivered":
                order.setSatus(3);
                break;
            case "cancel":
                order.setSatus(4);
                break;

        }
        if (order.getSatus()==4)
        {
            List<OrderDetailEntity> list = order.getOrderDetailList();
            for(OrderDetailEntity item : list)
            {
                ProductEntity product = productService.findById(item.getProductId());
                if (product==null)
                {
                    throw new RuntimeException("not found product");
                }
                product.setQuantity(item.getQuantity() + product.getQuantity());
                product.setSold(product.getSold() - item.getQuantity());
                productService.saveProduct(product);
            }

        }

        orderServiceImpl.saveOrder(order);
        return "redirect:/Admin_Order";
    }


}
