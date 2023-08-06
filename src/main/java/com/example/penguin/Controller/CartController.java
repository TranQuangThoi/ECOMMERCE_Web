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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
            model.addAttribute("cart",cart);
            if(cart==null)
            {
                CartEntity newCart = new CartEntity();
                model.addAttribute("newCart",newCart);
            }else {
                List<CartDetailEntity> cartDetailList = cart.getCartDetailList();
                Iterator<CartDetailEntity> iterator = cartDetailList.iterator();
                int total=0;
                // xét tổng tiền của sản phẩm ( có nhiều cái)
                // tránh trường hợp đổi s liệu ở dưới dữ liệu những kh cập nhật giá
                if(cartDetailList!=null)
                {
                    while (iterator.hasNext())
                    {
                        CartDetailEntity c = iterator.next();
                        c.setPrice(c.getProduct().getPrice() * c.getQuantity());
                    }
                    // tổng price
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
    private String addToCart(Model model, @PathVariable int id,
                             @RequestParam(name = "quantity") int quantity,
                             @RequestParam(name = "size") int size) {

        UserEntity user = (UserEntity) session.getAttribute("account");

        if (user != null) {
            CartEntity cart = cartService.findCartByUserId(user.getId());
            ProductEntity product = productService.findById(id);
            int totalPrice = 0;

            CartDetailEntity cartDetail = null;
            if (cart != null) {
                List<CartDetailEntity> cartDetailList = cart.getCartDetailList();

                if(cartDetailList!= null)
                {
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
                }
                for (CartDetailEntity a : cart.getCartDetailList()) {
                    totalPrice += a.getPrice();
                }


            } else {
               cart =new CartEntity();
               cart.setUserEntity(user);
               cartService.saveCart(cart);
               CartDetailEntity cartDetail1 = new CartDetailEntity();
               cartDetail1.setCart(cart);
               cartDetail1.setProduct(product);
               cartDetail1.setSize(size);
               cartDetail1.setQuantity(quantity);
               cartDetail1.setPrice(cartDetail1.getProduct().getPrice() * cartDetail.getQuantity());
               cartDetailService.saveCartDetail(cartDetail1);
                for (CartDetailEntity a : cart.getCartDetailList()) {
                    totalPrice += a.getPrice();
                }
            }


            cart.setTotalPrice(totalPrice);
            cartService.saveCart(cart);
        }

        return "redirect:/cart";
    }


    @GetMapping("/Cart/Delete/{id}")
    private String deleteCartItem(@PathVariable(name = "id") int id , Model model)
    {
        cartDetailService.deleteCartDetailById(id);
        return "redirect:/cart";
    }
    @PostMapping("/Cart/update")
    public String updateCart(HttpServletRequest request)
    {
        UserEntity user = (UserEntity) session.getAttribute("account");

            CartEntity cart = cartService.findCartByUserId(user.getId());

            List<CartDetailEntity> cartDetailList = cart.getCartDetailList();
            int i=0 ;
            String[]  Q = request.getParameterValues("quantity");


           for (CartDetailEntity c : cartDetailList)
           {

               c.setQuantity(Integer.parseInt(Q[i]));
               c.setPrice(c.getQuantity() * c.getProduct().getPrice());
               i++;
           }

        return "cart";
    }




}
