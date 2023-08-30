package com.example.penguin.Service;

import com.example.penguin.Entities.CartEntity;

public interface CartService {

     CartEntity findCartByUserId(int id);
     void saveCart(CartEntity cart);
     void deleteByCart(CartEntity cart);


}
