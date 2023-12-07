package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.CartDetailEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Repository.CartDetailReposity;
import com.example.penguin.Service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    CartDetailReposity cartDetailReposity;

    @Override
    public void saveCartDetail(CartDetailEntity cartDetailEntity) {
        cartDetailReposity.save(cartDetailEntity);
    }

    @Override
    public void deleteCartDetailById(int id) {

        cartDetailReposity.deleteById(id);
    }

    @Override
    public void deleteAllByProduct(ProductEntity product) {
        cartDetailReposity.deleteAllByProduct(product);
    }

//    public void saveCartDetail(CartDetailEntity cartDetailEntity)
//    {
//        cartDetailReposity.save(cartDetailEntity);
//    }
//
//    public void deleteCartDetailById(int id)
//    {
//        cartDetailReposity.deleteById(id);
//    }

}
