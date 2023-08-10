package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.CartDetailEntity;
import com.example.penguin.Entities.CartEntity;
import com.example.penguin.Repository.CartDetailReposity;
import com.example.penguin.Repository.CartRepository;
import com.example.penguin.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartDetailReposity cartDetailReposity;
    @Override
    public CartEntity findCartByUserId(int id)
    {
        return cartRepository.findByIdUser(id);
    }

    @Override
    public void saveCart(CartEntity cart)
    {
        cartRepository.save(cart);
    }

    @Override
    public void deleteByCart(CartEntity cart)
    {
        List<CartDetailEntity> cartDetailList = cart.getCartDetailList();
        for(CartDetailEntity cartDetail : cartDetailList)
        {
            cartDetailReposity.delete(cartDetail);
        }
        cartRepository.delete(cart);
    }


}
