package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.Mail;
import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {


    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendMail(Mail mail) {

       SimpleMailMessage message = new SimpleMailMessage();
       message.setFrom(mail.getMailFrom());
       message.setTo(mail.getMailTo());
       message.setSubject(mail.getMailSubject());
       message.setText(mail.getMailContent());
       javaMailSender.send(message);

    }

    @Override
    public void sendOrderMail(OrderEntity order) {

        Mail mail = new Mail();
        mail.setMailFrom("thoidepzai@gmail.com");
        mail.setMailTo(order.getUserEntity().getEmail());
        mail.setMailSubject("Web Bán hàng Online - Bạn đặt hàng thành công");
        String content = "Tổng giá : " + order.getTotalPrice() +"\n";

        List<OrderDetailEntity> orderDetailList = order.getOrderDetailList();
        if (orderDetailList != null) {
            for (OrderDetailEntity item : orderDetailList) {
                content += "Sản phẩm :  " +item.getProductName() + "    số lượng :  " + item.getQuantity() + "Gía :  " + item.getPrice() + "VND"+"\n";
            }
        } else {
            content += "Không có chi tiết đơn hàng."+"\n";
        }

        content += "Sản phẩm được đặt bởi:  " + order.getUserEntity().getName()+"\n";
        content +="Ngày đặt : " +order.getOrderDate() +"\n";
        content +="Địa chỉ nhận hàng : " + order.getDeliveryAddress() +"\n";
        content +="Số điện thoại  : " +order.getUserEntity().getPhone();
        mail.setMailContent(content);
        sendMail(mail);

    }
}
