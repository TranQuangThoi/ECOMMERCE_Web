package com.example.penguin.Repository;

import com.example.penguin.Entities.ImagesEntity;
import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReposity extends JpaRepository<ProductEntity , Integer> {

    public ProductEntity findById(int id);

    @Query("select pro from ProductEntity pro where pro.category.idCategory=:id")
    List<ProductEntity> findByCate(int id);

    @Query("select pro from ProductEntity pro where pro.category.idCategory=:cateId ")
    List<ProductEntity> findProdcutRelateCate(int cateId,Pageable pageable);

    @Query("select pro FROM ProductEntity pro order by pro.sold DESC ")
    List<ProductEntity> findTop10Hot(Pageable pageable);
}
