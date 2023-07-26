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
    @JoinColumn(name = "idProduct")
    private ProductEntity product;

    @OneToOne
    @JoinColumn(name ="idOrder")
    private OrderEntity order;

}
