package com.example.penguin.Repository;

import com.example.penguin.Entities.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageReposity extends JpaRepository<ImagesEntity , Integer> {

    // lấy ra hình ảnh của sản phẩm theo id sản phẩm
    @Query("select i from ImagesEntity i where  i.product.idProduct =:idProduct")
    List<ImagesEntity> findByPro(int idProduct);



}
