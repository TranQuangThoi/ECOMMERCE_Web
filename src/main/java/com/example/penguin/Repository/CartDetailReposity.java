package com.example.penguin.Repository;

import com.example.penguin.Entities.CartDetailEntity;
import com.example.penguin.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartDetailReposity extends JpaRepository<CartDetailEntity,Integer> {

    @Transactional
    void deleteAllByProduct(ProductEntity product);
}
