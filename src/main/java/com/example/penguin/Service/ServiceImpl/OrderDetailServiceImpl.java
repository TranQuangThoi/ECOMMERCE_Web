package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Repository.OrderDetailRepository;
import com.example.penguin.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public void saveOrderDetail(OrderDetailEntity orderDetail)
    {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetailEntity> findHistoryByUserId(int id) {

        return orderDetailRepository.findAllByUserId(id);
    }

    @Override
    public void deleteItemOrderDetail(OrderDetailEntity orderDetail) {

        orderDetailRepository.delete(orderDetail);
    }

    @Override
    public Long countSoldPro(int productId) {
       return orderDetailRepository.countSoldProd(productId);
    }


}
