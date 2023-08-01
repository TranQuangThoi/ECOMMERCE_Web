package com.example.penguin.Controller;

import com.example.penguin.Entities.CartDetailEntity;
import com.example.penguin.Entities.CartEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Entities.UserEntity;
import com.example.penguin.Service.CartDetailService;
import com.example.penguin.Service.CartService;
import com.example.penguin.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartController {


    @Autowired
    HttpSession session;
    @Autowired
    CartService cartService;
    @Autowired
    CartDetailService cartDetailService;
    @Autowired
    ProductService productService;

    @GetMapping("cart")
    public String showCart(Model model)
    {
        UserEntity user = (UserEntity) session.getAttribute("account");

        if(user!= null)
        {

            CartEntity cart = cartService.findCartByUserId(user.getId());
            if(cart==null)
            {
                CartEntity newCart = new CartEntity();
                model.addAttribute("newCart",newCart);
            }else {
                List<CartDetailEntity> cartDetailList = cart.getCartDetailList();
                model.addAttribute("cart",cart);
                model.addAttribute("cartDetailList" ,cartDetailList);
            }
        }
        return "cart";
    }
    @GetMapping("/Cart/{id}")
    private String addToCart(Model model, @PathVariable int id,
                             @RequestParam(name = "quantity") int quantity,
                             @RequestParam(name = "size") int size) {

        UserEntity user = (UserEntity) session.getAttribute("account");

        if (user != null) {
            CartEntity cart = cartService.findCartByUserId(user.getId());
            ProductEntity product = productService.findById(id);

            if (cart != null) {
                List<CartDetailEntity> cartDetailList = cart.getCartDetailList();
                CartDetailEntity cartDetail = null;

//
                Iterator<CartDetailEntity> iterator = cartDetailList.iterator();
//                Duyệt các phần tử từ đầu đến cuối của một collection.
//                Iterator cho phép xóa phần tử khi lặp một collection.
                boolean found = false;

                while (iterator.hasNext()) {
                    CartDetailEntity c = iterator.next();
                    if (c.getProduct().getIdProduct() == id) {
                        c.setSize(size);
                        c.setQuantity(c.getQuantity() + quantity);

                        if (product.getQuantity() >= c.getQuantity()) {
                            c.setPrice(c.getQuantity() * c.getProduct().getPrice());
                            cartDetail = c;
                            cartDetailService.saveCartDetail(cartDetail);
                        } else {
                            // Xóa cart detail nếu số lượng vượt quá số lượng sản phẩm có sẵn
                            iterator.remove();
                        }

                        found = true;
                        break;
                    }
                }

                // Nếu found là false
                if (!found) {
                    cartDetail = new CartDetailEntity();
                    cartDetailList.add(cartDetail);
                    cartDetail.setCart(cart);
                    cartDetail.setQuantity(quantity);
                    cartDetail.setSize(size);
                    cartDetail.setProduct(product);
                    cartDetail.setPrice(cartDetail.getQuantity() * product.getPrice());
                    cartDetailService.saveCartDetail(cartDetail);
                }
            } else {
                CartEntity cartNew = new CartEntity();
            }

            int totalPrice = 0;
            for (CartDetailEntity a : cart.getCartDetailList()) {
                totalPrice += a.getPrice();
            }
            cart.setTotalPrice(totalPrice);
            cartService.saveCart(cart);
        }

        return "redirect:/cart";
    }




}
