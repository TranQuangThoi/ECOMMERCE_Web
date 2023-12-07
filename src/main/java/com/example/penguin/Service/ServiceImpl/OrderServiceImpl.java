package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Repository.OrderRepository;
import com.example.penguin.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
        Pageable pageable = PageRequest.of(pageNumber -1,pageSize, Sort.by(Sort.Direction.DESC, "orderDate"));
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public Page<OrderEntity> findPageByUser(int pageNumber, int pageSize , int userId) {

        Pageable pageable = PageRequest.of(pageNumber-1 , pageSize, Sort.by(Sort.Direction.DESC, "orderDate"));
        return orderRepository.findOrderEntitiesByUserEntityId(userId,pageable);
    }

    @Override
    public List<OrderEntity> findOrderOfDate(String startDate, String endDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);

            List<OrderEntity> orderEntityList = orderRepository.findOrderSoldByDateRange(start, end);
            System.out.println(orderEntityList.size());
            return orderEntityList;
        } catch (ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
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

    @Override
    public OrderEntity findById(int id) {
        OrderEntity order = orderRepository.findById(id).orElse(null);
        return order;
    }


}
