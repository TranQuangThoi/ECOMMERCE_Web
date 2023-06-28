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

}
