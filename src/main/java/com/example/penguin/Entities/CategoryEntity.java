package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table
@Data
@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idCategory;

    private String nameCategory;
    private int status;
    private long productCount;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> productEntities;

}
