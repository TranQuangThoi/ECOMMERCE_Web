package com.example.penguin.Repository;

import com.example.penguin.Entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity , Integer> {


    @Query("select is from OrderEntity is where is.satus=3")
    List<OrderEntity> sales_figure();


    // lọc theo phạm vi ngày
    @Query (value = "select * from order_entity  o where o.order_date >=?1 AND o.order_date <=?2 AND o.satus=3", nativeQuery = true)
    List<OrderEntity> findOrderSoldByDateRange(String start , String end);

//    @Query ( "select o from OrderEntity  o where o.orderDate >=?1 AND o.orderDate <=?2 AND o.satus=3")
//    List<OrderEntity> findOrderSoldByDateRange(String start , String end);
}