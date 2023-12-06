package com.example.penguin.Controller;

import com.example.penguin.Entities.CartDetailEntity;
import com.example.penguin.Entities.CartEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Entities.UserEntity;
import com.example.penguin.Service.ServiceImpl.CartDetailServiceImpl;
import com.example.penguin.Service.ServiceImpl.CartServiceImpl;
import com.example.penguin.Service.ServiceImpl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartController {


    @Autowired
    HttpSession session;
    @Autowired
    CartServiceImpl cartServiceImpl;
    @Autowired
    CartDetailServiceImpl cartDetailServiceImpl;
    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("cart")
    public String showCart(Model model)
    {
        UserEntity user = (UserEntity) session.getAttribute("account");

        if(user!= null)
        {

            CartEntity cart = cartServiceImpl.findCartByUserId(user.getId());
            model.addAttribute("cart",cart);
            if(cart==null)
            {
                CartEntity newCart = new CartEntity();
                model.addAttribute("newCart",newCart);
            }else {
                List<CartDetailEntity> cartDetailList = cart.getCartDetailList();
                Iterator<CartDetailEntity> iterator = cartDetailList.iterator();
                int total=0;
                if(cartDetailList!=null)
                {
                    while (iterator.hasNext())
                    {
                        CartDetailEntity c = iterator.next();
                        c.setPrice(c.getProduct().getPrice() * c.getQuantity());
                    }
                    for(CartDetailEntity c : cartDetailList)
                    {
                        total += c.getPrice();
                    }
                    cart.setTotalPrice(total);
                    model.addAttribute("cartDetailList" ,cartDetailList);
                }
            }
        }
        return "cart";
    }
    @GetMapping("/Cart/{id}")
    private String addToCart( @PathVariable int id,
                             @RequestParam(name = "quantity") int quantity,
                             @RequestParam(name = "size") int size) {

        UserEntity user = (UserEntity) session.getAttribute("account");

        if (user != null) {
            CartEntity cart = cartServiceImpl.findCartByUserId(user.getId());
            ProductEntity product = productServiceImpl.findById(id);
            int totalPrice = 0;

            CartDetailEntity cartDetail = null;
            if (cart != null) {
                List<CartDetailEntity> cartDetailList = cart.getCartDetailList();

                if(cartDetailList!= null)
                {
                    Iterator<CartDetailEntity> iterator = cartDetailList.iterator();
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
                                cartDetailServiceImpl.saveCartDetail(cartDetail);
                            } else {
                                // Xóa cart detail nếu số lượng vượt quá số lượng sản phẩm có sẵn
                                iterator.remove();
                            }

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        cartDetail = new CartDetailEntity();
                        cartDetailList.add(cartDetail);
                        cartDetail.setCart(cart);
                        cartDetail.setQuantity(quantity);
                        cartDetail.setSize(size);
                        cartDetail.setProduct(product);
                        cartDetail.setPrice(cartDetail.getQuantity() * product.getPrice());
                        cartDetailServiceImpl.saveCartDetail(cartDetail);
                    }
                }
                for (CartDetailEntity a : cart.getCartDetailList()) {
                    totalPrice += a.getPrice();
                }


            } else {
               cart =new CartEntity();
               cart.setUserEntity(user);
               cartServiceImpl.saveCart(cart);
               CartDetailEntity cartDetail1 = new CartDetailEntity();
               cartDetail1.setCart(cart);
               cartDetail1.setProduct(product);
               cartDetail1.setSize(size);
               cartDetail1.setQuantity(quantity);
               cartDetail1.setPrice(cartDetail1.getProduct().getPrice() * cartDetail1.getQuantity());
                cartDetailServiceImpl.saveCartDetail(cartDetail1);
               if(cart.getCartDetailList()!= null)
               {
                   for (CartDetailEntity a : cart.getCartDetailList()) {
                       totalPrice += a.getPrice();
                   }
               }


            }

            cart.setTotalPrice(totalPrice);
            cartServiceImpl.saveCart(cart);
        }

        return "redirect:/cart";
    }


    @GetMapping("/Cart/Delete/{id}")
    private String deleteCartItem(@PathVariable(name = "id") int id , Model model)
    {
        cartDetailServiceImpl.deleteCartDetailById(id);
        return "redirect:/cart";
    }
    @PostMapping("/Cart/update")
    public String updateCart(@RequestParam(name = "quantity",required = false) int[] quantity , Model model)
    {
        UserEntity customer = (UserEntity) session.getAttribute("account");
        if(customer != null){
            CartEntity cart = cartServiceImpl.findCartByUserId(customer.getId());
            if(cart != null){
                List<CartDetailEntity> list = cart.getCartDetailList();
                if(list != null){
                    int i = 0;
                    for(CartDetailEntity c : list){
                        int quantityParam = quantity[i];
                        c.setQuantity(quantityParam);
                        c.setPrice(c.getQuantity() * c.getProduct().getPrice());
                          i++;
                    }
                    int total = 0;
                    for (CartDetailEntity c : list)
                        total += c.getPrice();
                    cart.setTotalPrice(total);
                    cart.setCartDetailList(list);
                    cartServiceImpl.saveCart(cart);
                }
            }
        }
        return "redirect:/cart";
    }




}
