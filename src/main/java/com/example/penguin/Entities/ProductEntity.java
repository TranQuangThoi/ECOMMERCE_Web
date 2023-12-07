package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    private int price;
    @Column(nullable = true)
    private int discount;

    private int rating;
    private String reviews;
    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private int quantity = 0;

    private int available;

    private int size;

    @OneToMany(mappedBy = "product")
    private List<ImagesEntity>imageList;
    private int sold;

}
