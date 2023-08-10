package com.example.penguin.Controller;

import com.example.penguin.Entities.*;
import com.example.penguin.Service.ServiceImpl.*;
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
    CartServiceImpl cartServiceImpl;

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    OrderDetailServiceImpl orderDetailServiceImpl;
    @Autowired
    UserAccServiceImpl userAccServiceImpl;
    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("checkOut")
    public String pageCheckOut(Model model)
    {
        UserEntity user = (UserEntity) session.getAttribute("account");
            CartEntity cart = cartServiceImpl.findCartByUserId(user.getId());
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
        CartEntity cart = cartServiceImpl.findCartByUserId(idUser);
        List<CartDetailEntity> cartDetailEntityList = cart.getCartDetailList();
            OrderEntity order = new OrderEntity();
            order.setUserEntity(user);
            order.setTotalPrice(cart.getTotalPrice());
            order.setSatus(1);
            order.setDeliveryAddress(address);
            orderServiceImpl.saveOrder(order);


       for (CartDetailEntity cartDetail :cartDetailEntityList)
       {
           OrderDetailEntity orderDetail = new OrderDetailEntity();
           orderDetail.setQuantity(cartDetail.getQuantity());
           orderDetail.setPrice(cartDetail.getPrice());
           orderDetail.setOrder(order);
           orderDetail.setProductName(cartDetail.getProduct().getProductName());
           orderDetailServiceImpl.saveOrderDetail(orderDetail);

           ProductEntity product = productServiceImpl.findById(cartDetail.getProduct().getIdProduct());
           product.setQuantity(product.getQuantity()-orderDetail.getQuantity());

       }

       cartServiceImpl.deleteByCart(cart);
        return "redirect:/cart";
    }

}