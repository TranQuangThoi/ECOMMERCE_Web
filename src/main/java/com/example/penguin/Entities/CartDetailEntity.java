package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class CartDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;
    private int quantity ;
    private int price;
    private int size;
    @ManyToOne
    @JoinColumn(name="productId")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "cartId")
    private CartEntity cart;

}
