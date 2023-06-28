package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class CommentEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idComment;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity product;


}
