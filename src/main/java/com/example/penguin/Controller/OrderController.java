package com.example.penguin.Controller;

import com.example.penguin.Entities.*;
import com.example.penguin.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    HttpSession session;
    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    UserAccService userAccService;
    @Autowired
    ProductService productService;

    @GetMapping("checkOut")
    public String pageCheckOut(Model model)
    {
        UserEntity user = (UserEntity) session.getAttribute("account");
            CartEntity cart = cartService.findCartByUserId(user.getId());
        List<CartDetailEntity> cartDetailList = cart.getCartDetailList();


        model.addAttribute("cartDetailList",cartDetailList);
        model.addAttribute("cart",cart);
        return"checkOut";
    }
    @PostMapping("Cart/checkOut/{id}")
   public String compliteCheckOut(@PathVariable(name = "id")int idUser , Model model ,
                                  @RequestParam(name="address") String address)
    {

        UserEntity user = (UserEntity) session.getAttribute("account");
        CartEntity cart = cartService.findCartByUserId(idUser);
        List<CartDetailEntity> cartDetailEntityList = cart.getCartDetailList();
            OrderEntity order = new OrderEntity();
            order.setUserEntity(user);
            order.setTotalPrice(cart.getTotalPrice());
            order.setSatus(1);
            order.setDeliveryAddress(address);
            orderService.saveOrder(order);


       for (CartDetailEntity cartDetail :cartDetailEntityList)
       {
           OrderDetailEntity orderDetail = new OrderDetailEntity();
           orderDetail.setQuantity(cartDetail.getQuantity());
           orderDetail.setPrice(cartDetail.getPrice());
           orderDetail.setOrder(order);
           orderDetail.setProductName(cartDetail.getProduct().getProductName());
           orderDetailService.saveOrderDetail(orderDetail);

           ProductEntity product = productService.findById(cartDetail.getProduct().getIdProduct());
           product.setQuantity(product.getQuantity()-orderDetail.getQuantity());

       }

       cartService.deleteByCart(cart);
        return "redirect:/cart";
    }

}
