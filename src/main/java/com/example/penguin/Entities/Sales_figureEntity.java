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
    @JoinColumn(name = "idOrderDetail")
    private OrderDetailEntity orderDetailEntity;

    @ManyToOne
    @JoinColumn(name ="id")
    private UserAccountEntity userAccount;

    private int totalOrder;
    private long totalPrice;

}
