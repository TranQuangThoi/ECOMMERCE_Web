package com.example.penguin.Service;

import com.example.penguin.Entities.CartEntity;
import com.example.penguin.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public CartEntity findCartByUserId(int id)
    {
        return cartRepository.findByIdUser(id);
    }

    public void saveCart(CartEntity cart)
    {
        cartRepository.save(cart);
    }


}
