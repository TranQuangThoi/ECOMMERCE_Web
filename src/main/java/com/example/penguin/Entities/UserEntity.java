package com.example.penguin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Table
@Entity(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id ;

    private String name ;

    @Column(unique = true)
    private String phone;

    private String email ;
    private String address;
    private String password;
    private Date birthDate;
    private int role ;
    private String avatar;

    @OneToOne(mappedBy = "userEntity")
    private CartEntity cart;

    @OneToMany(mappedBy = "userEntity")
    @JsonIgnore
    private List<OrderEntity> order;


}
