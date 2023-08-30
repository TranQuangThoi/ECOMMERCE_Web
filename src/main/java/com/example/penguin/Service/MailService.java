package com.example.penguin.Service;

import com.example.penguin.Entities.Mail;
import com.example.penguin.Entities.OrderEntity;

public interface MailService {

    void sendMail(Mail mail);
    void sendOrderMail(OrderEntity order);

}
