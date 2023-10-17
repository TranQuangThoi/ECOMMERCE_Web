package com.example.penguin.Service;

import com.example.penguin.Entities.OrderDetailEntity;

import java.util.List;

public interface OrderDetailService {

    void saveOrderDetail(OrderDetailEntity orderDetail);

    List<OrderDetailEntity> findHistoryByUserId(int id);

    void deleteItemOrderDetail(OrderDetailEntity orderDetail);
    Long countSoldPro(int productId);
}
