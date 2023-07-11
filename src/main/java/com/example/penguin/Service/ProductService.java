package com.example.penguin.Service;

import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Repository.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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

    public ProductEntity findById(int id)
    {
        return productReposity.findById(id);
    }

    public Page<ProductEntity> findPage(int pageNumber , int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber -1,pageSize);
        return this.productReposity.findAll(pageable);
    }

    public void deleteById(int id)
    {
        ProductEntity product = productReposity.findById(id);
        productReposity.delete(product);
    }

    public void delete(ProductEntity product)
    {
        productReposity.delete(product);
    }


    // trả về danh sách sản phẩm theo category
    public List<ProductEntity> findListById(int id)
    {

        return (List<ProductEntity>)productReposity.findByCate(id);
    }


}
