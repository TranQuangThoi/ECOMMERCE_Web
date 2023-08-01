package com.example.penguin.Service;

import com.example.penguin.Repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
}
