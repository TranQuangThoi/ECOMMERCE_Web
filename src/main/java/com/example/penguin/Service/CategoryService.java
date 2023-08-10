package com.example.penguin.Service;

import com.example.penguin.Entities.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> findAll();
    void deleteById(int id);

    void saveCategory(CategoryEntity category);

    CategoryEntity findById(int id);
}
