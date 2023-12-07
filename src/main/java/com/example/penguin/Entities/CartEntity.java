package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "cart")
@Table
@Data
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private long totalPrice;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "cart")
    private List<CartDetailEntity> cartDetailList;


}
