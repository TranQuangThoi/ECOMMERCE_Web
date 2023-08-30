package com.example.penguin.Repository;

import com.example.penguin.Entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Integer> {


    @Query("select o from cart o where o.userEntity.id=:id")
    CartEntity findByIdUser(int id);






}
