package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "OrderEntity")
@Table
@Data
public class OrderEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    private java.util.Date orderDate=new Date(new java.util.Date().getTime());
    protected long totalPrice;
    private int satus;



}
