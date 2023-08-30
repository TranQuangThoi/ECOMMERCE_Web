package com.example.penguin.Service;

import com.example.penguin.Entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<OrderEntity> findAll();
    List<OrderEntity> findOrderSold();

    Page<OrderEntity> findPage(int pageNumber , int pageSize );

    Page<OrderEntity> findPageByUser(int pageNumber , int pageSize , int userId);

    List<OrderEntity> findOrderOfDate(String startDate, String endDate);

    List<OrderEntity> findOrderByIdUser(int id);

    void saveOrder(OrderEntity orderEntity);




}
