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
    private int idOrder;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserAccountEntity userAccount;

    private java.util.Date orderDate=new Date(new java.util.Date().getTime());
    protected long totalPrice;
    private int satus;



}
