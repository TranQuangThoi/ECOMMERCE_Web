package com.example.penguin.Controller;

import com.example.penguin.Entities.*;
import com.example.penguin.Repository.ProductReposity;
import com.example.penguin.Service.*;
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
    private CartService cartServiceImpl;

    @Autowired
    private OrderService orderServiceImpl;

    @Autowired
    private OrderDetailService orderDetailServiceImpl;

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private MailService mailService;
    @Autowired
    private ProductReposity productReposity;

    @GetMapping("checkOut")
    public String pageCheckOut(Model model) {
        UserEntity user = (UserEntity) session.getAttribute("account");
        CartEntity cart = cartServiceImpl.findCartByUserId(user.getId());
        List<CartDetailEntity> cartDetailList = cart.getCartDetailList();


        model.addAttribute("cartDetailList", cartDetailList);
        model.addAttribute("cart", cart);
        return "checkOut";
    }

    @PostMapping("Cart/checkOut/{id}")
    public String compliteCheckOut(@PathVariable(name = "id") int idUser,
                                   @RequestParam(name = "address") String address,
                                   @RequestParam(name = "email") String email,
                                   @RequestParam(name = "phone") String phone) {

        UserEntity user = (UserEntity) session.getAttribute("account");
        CartEntity cart = cartServiceImpl.findCartByUserId(idUser);
        List<CartDetailEntity> cartDetailEntityList = cart.getCartDetailList();
        OrderEntity order = new OrderEntity();
        order.setUserEntity(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setSatus(0);
        order.setDeliveryAddress(address);
        order.setEmail(email);
        order.setPhone(phone);
        orderServiceImpl.saveOrder(order);


        for (CartDetailEntity cartDetail : cartDetailEntityList) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setQuantity(cartDetail.getQuantity());
            orderDetail.setPrice(cartDetail.getPrice());
            orderDetail.setOrder(order);
            orderDetail.setProductName(cartDetail.getProduct().getProductName());
            orderDetail.setProductId(cartDetail.getProduct().getIdProduct());
            orderDetailServiceImpl.saveOrderDetail(orderDetail);

            ProductEntity product = productServiceImpl.findById(cartDetail.getProduct().getIdProduct());
            product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
            product.setSold(cartDetail.getQuantity() + product.getSold());
            productReposity.save(product);

        }

        mailService.sendOrderMail(order);
        cartServiceImpl.deleteByCart(cart);
        return "redirect:/cart";
    }

}
