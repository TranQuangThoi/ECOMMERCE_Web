package com.example.penguin.Service;

import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Repository.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductReposity productReposity;

    public List<ProductEntity> findAll()
    {
        return productReposity.findAll();
    }

    public void saveProduct(ProductEntity product)
    {
        productReposity.save(product);
    }

}
