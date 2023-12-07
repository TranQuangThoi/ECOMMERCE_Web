package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class OrderDetailEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idOrderDetail;


    @ManyToOne
    @JoinColumn(name ="OrderId")
    private OrderEntity order;

    private int quantity;
    private int price;
    private String productName;
    private int productId;


}
