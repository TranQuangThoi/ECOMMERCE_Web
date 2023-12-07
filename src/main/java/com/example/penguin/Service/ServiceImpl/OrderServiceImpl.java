package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Repository.OrderRepository;
import com.example.penguin.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

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
    public Page<OrderEntity> findPageByUser(int pageNumber, int pageSize , int userId) {

        Pageable pageable = PageRequest.of(pageNumber-1 , pageSize);
        return orderRepository.findOrderEntitiesByUserEntityId(userId,pageable);
    }

    @Override
    public List<OrderEntity> findOrderOfDate(String startDate, String endDate) {
//        Date start = java.sql.Date.valueOf(startDate);
//        Date end = java.sql.Date.valueOf(endDate);
        List<OrderEntity> orderEntityList = orderRepository.findOrderSoldByDateRange(startDate,endDate);
        System.out.println(orderEntityList);
        return orderEntityList;
    }


    @Override
    public List<OrderEntity> findOrderByIdUser(int id)
    {
        List<OrderEntity> listOrder = orderRepository.findOrderEntitiesByUserEntityId(id);
        return listOrder ;
    }

    @Override
    public void saveOrder(OrderEntity orderEntity)
    {
        orderRepository.save(orderEntity);
    }



}
