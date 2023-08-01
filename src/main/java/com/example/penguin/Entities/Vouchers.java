package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table
@Data
public class Vouchers {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int voucherId;
    private Date startDate;
    private Date exprie;
    private String description;
}
