package com.example.penguin.Repository;

import com.example.penguin.Entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryReposity extends JpaRepository<CategoryEntity, Integer> {

    CategoryEntity findById(int id);

}
