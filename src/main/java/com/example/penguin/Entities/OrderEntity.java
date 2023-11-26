package com.example.penguin.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity(name = "OrderEntity")
@Table
@Data
public class OrderEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int orderId;

//    (fetch = FetchType.EAGER) , khi lấy một đối tượng của lớp OrderEntity,
//    JPA/Hibernate sẽ tự động tải dữ liệu của thực thể liên quan UserEntity
//    (quan hệ Many-to-One) từ cơ sở dữ liệu cùng với OrderEntity.
//    Điều này có nghĩa là dữ liệu của UserEntity sẽ được tải ngay lập tức mà không cần phải thực hiện truy vấn lần nữa
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    // mappedBy phải trùng tên bên orderdetail
    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetailList;

    private Date orderDate=new Date(new java.util.Date().getTime());
    protected long totalPrice;
    private int satus;
    private String deliveryAddress;
    private String email;
    private String phone;


}
