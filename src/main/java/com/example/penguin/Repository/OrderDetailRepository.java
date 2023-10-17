package com.example.penguin.Repository;

import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity , Integer> {


    @Query("SELECT O FROM OrderDetailEntity O INNER JOIN O.order u where u.userEntity.id =: userId")
    List<OrderDetailEntity> findAllByUserId(int userId );


    @Query("select sum(od.quantity) FROM OrderDetailEntity od where od.product.idProduct =:productId")
    Long countSoldProd(int productId);
}
