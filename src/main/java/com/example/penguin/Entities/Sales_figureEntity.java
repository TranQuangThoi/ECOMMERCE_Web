package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Sales_figureEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idSelled;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name ="id")
    private UserAccountEntity userAccount;


    private long totalPrice;

}
