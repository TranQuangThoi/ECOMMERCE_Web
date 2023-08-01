package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class ImagesEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idImage;


    private String url;

    @ManyToOne
    @JoinColumn(name="ProductId")
    private ProductEntity product;


}
