package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Repository.OrderRepository;
import com.example.penguin.Service.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderDetail {

    @Autowired
    OrderRepository orderRepository;

    @Override
   public List<OrderEntity> findAll()
    {
        return  orderRepository.findAll();
    }

    @Override
    public List<OrderEntity> findOrderSold()
    {
        return orderRepository.sales_figure();
    }

    @Override
    public Page<OrderEntity> findPage(int pageNumber , int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber -1,pageSize);
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public List<OrderEntity> findOrderOfDate(String startDate, String endDate) {

//        // Chuyển đổi kiểu dữ liệu từ String sang java.util.Date hoặc java.sql.Date
//        Date start = java.sql.Date.valueOf(startDate);
//        Date end = java.sql.Date.valueOf(endDate);
        return orderRepository.findOrderSoldByDateRange(startDate, endDate);
    }


    @Override
    public OrderEntity findOrderByIdUser(int id)
    {
        return orderRepository.findOrderByIdUser(id);
    }

    @Override
    public void saveOrder(OrderEntity orderEntity)
    {
        orderRepository.save(orderEntity);
    }


}
