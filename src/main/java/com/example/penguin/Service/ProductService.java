package com.example.penguin.Service;

import com.example.penguin.Entities.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<ProductEntity> findAll();
    void saveProduct(ProductEntity product);

    ProductEntity findById(int id);

    Page<ProductEntity> findPage(int pageNumber , int pageSize );

    void delete(ProductEntity product);

    List<ProductEntity> findProByCategoryId(int id);

    void deleteImageByPro(int id);
    void deleteProById(int id);

}
