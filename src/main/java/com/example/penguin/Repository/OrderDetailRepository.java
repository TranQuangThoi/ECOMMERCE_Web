package com.example.penguin.Repository;

import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity , Integer> {


    @Query("SELECT O FROM OrderDetailEntity O INNER JOIN O.order u where u.userEntity.id =: userId")
    List<OrderDetailEntity> findAllByUserId(int userId );
}
