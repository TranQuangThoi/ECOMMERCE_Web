package com.example.penguin.Service;

import com.example.penguin.Entities.OrderEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderDetail {

    List<OrderEntity> findAll();
    List<OrderEntity> findOrderSold();

    Page<OrderEntity> findPage(int pageNumber , int pageSize );

    List<OrderEntity> findOrderOfDate(String startDate, String endDate);

    OrderEntity findOrderByIdUser(int id);

    void saveOrder(OrderEntity orderEntity);


}
