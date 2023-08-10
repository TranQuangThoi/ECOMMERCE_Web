package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Repository.OrderDetailRepository;
import com.example.penguin.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public void saveOrderDetail(OrderDetailEntity orderDetail)
    {
        orderDetailRepository.save(orderDetail);
    }
}
