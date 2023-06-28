package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.repository.query.Param;

@Entity
@Table
@Data
public class ImagesEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idImage;

    private String url;

    @ManyToOne
    @JoinColumn(name="idProduct")
    private ProductEntity product;



}
