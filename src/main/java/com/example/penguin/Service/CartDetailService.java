package com.example.penguin.Service;
import com.example.penguin.Entities.CartDetailEntity;
import com.example.penguin.Repository.CartDetailReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailService {

    @Autowired
    CartDetailReposity cartDetailReposity;

    public void saveCartDetail(CartDetailEntity cartDetailEntity)
    {
        cartDetailReposity.save(cartDetailEntity);
    }

    public void deleteCartDetailById(int id)
    {
        cartDetailReposity.deleteById(id);
    }

}
