package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table
@Data
public class OrderEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idOrder;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserAccountEntity userAccount;

    private Date orderDate;
    protected long totalPrice;
    private int satus;
    private int payment;


}
