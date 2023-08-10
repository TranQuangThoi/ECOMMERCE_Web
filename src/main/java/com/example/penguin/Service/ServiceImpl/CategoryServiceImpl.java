package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Repository.CategoryReposity;
import com.example.penguin.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryReposity categoryReposity;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryReposity.findAll();
    }

    @Override
    public void deleteById(int id) {

        categoryReposity.deleteById(id);
    }

    @Override
    public void saveCategory(CategoryEntity category) {

        categoryReposity.save(category);
    }

    @Override
    public CategoryEntity findById(int id) {
        return categoryReposity.findById(id);
    }

}
