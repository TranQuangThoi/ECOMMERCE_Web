package com.example.penguin.Service;

import com.example.penguin.Entities.CartDetailEntity;
import com.example.penguin.Entities.ProductEntity;

public interface CartDetailService {


     void saveCartDetail(CartDetailEntity cartDetail);
     void deleteCartDetailById(int id);
     void deleteAllByProduct(ProductEntity product);
}
