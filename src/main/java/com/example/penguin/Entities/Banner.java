package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idBanner;
    private String name;
}
