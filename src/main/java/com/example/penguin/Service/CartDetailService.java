package com.example.penguin.Service;

import com.example.penguin.Entities.CartDetailEntity;

public interface CartDetailService {


     void saveCartDetail(CartDetailEntity cartDetail);
     void deleteCartDetailById(int id);
}
