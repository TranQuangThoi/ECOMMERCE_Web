package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class ProductEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idProduct;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private CategoryEntity category;

    private String productName;
    private String price;
    private Double discount;
    private int rating;
    private String reviews;
    private String description;
    private long quantity;


}
