package com.example.penguin.Repository;

import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Entities.UserAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReposity extends JpaRepository<ProductEntity , Integer> {

    public ProductEntity findById(int id);

    // lấy danh sách sản phẩm theo idcategory
    @Query("select pro from ProductEntity pro where pro.category.idCategory=:id")
    List<ProductEntity> findByCate(int id);




}
