package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table
@Data
public class OrderDetailEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idOrderDetail;


    @ManyToOne
    @JoinColumn(name ="OrderEntity")
    private OrderEntity order;

    private int quantity;
    private int price;
    private String productName;

    @ManyToOne
    @JoinColumn(name = "productId")
    @ToString.Exclude
    private ProductEntity product;




}
