package com.example.penguin.Service;

import com.example.penguin.Entities.CategoryEntity;
import com.example.penguin.Repository.CategoryReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService  {

    @Autowired
    CategoryReposity categoryReposity;

    public List<CategoryEntity> findAll()
    {
        return categoryReposity.findAll();
    }

    public void deleteById(int id)
    {
         categoryReposity.deleteById(id);
    }


    public void saveCategory(CategoryEntity categoryEntity)
    {
         categoryReposity.save(categoryEntity);
    }





    public CategoryEntity findById(int id) {

        return categoryReposity.findById(id);
    }
}
