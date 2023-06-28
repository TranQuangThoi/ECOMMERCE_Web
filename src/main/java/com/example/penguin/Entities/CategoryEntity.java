package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Data
@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idCategory;

    private String nameCategory;
    private String subCategory;

}
