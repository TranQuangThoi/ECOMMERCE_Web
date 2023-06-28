package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Cart {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idCart;

    @OneToOne
    @JoinColumn(name = "id")
    private UserAccountEntity userAccount;

    private long totalPrice;

}
