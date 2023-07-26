package com.example.penguin.Repository;

import com.example.penguin.Entities.Sales_figureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Sales_figureReposity extends JpaRepository<Sales_figureEntity,Integer> {

    @Query("select is from Sales_figureEntity is " +
            "inner join is.orderEntity o " +
            "WHERE o.satus=3")
    List<Sales_figureEntity> findAll();

}
