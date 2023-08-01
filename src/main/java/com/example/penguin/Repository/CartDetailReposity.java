package com.example.penguin.Repository;

import com.example.penguin.Entities.CartDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailReposity extends JpaRepository<CartDetailEntity,Integer> {
}
