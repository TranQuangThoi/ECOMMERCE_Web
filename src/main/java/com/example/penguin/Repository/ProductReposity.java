package com.example.penguin.Repository;

import com.example.penguin.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReposity extends JpaRepository<ProductEntity , Integer> {

}
