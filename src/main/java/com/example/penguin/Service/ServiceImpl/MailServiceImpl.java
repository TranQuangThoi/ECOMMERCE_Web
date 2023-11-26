package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.Mail;
import com.example.penguin.Entities.OrderDetailEntity;
import com.example.penguin.Entities.OrderEntity;
import com.example.penguin.Service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        mail.setMailFrom("tranquangthoi420632@gmail.com");
        mail.setMailTo(order.getEmail());
        mail.setMailSubject("Cảm ơn đã tin tưởng và mua hàng ở cửa hàng chúng tôi");
        String content = "Tổng giá : " + order.getTotalPrice() +"\n";

        List<OrderDetailEntity> orderDetailList = order.getOrderDetailList();
        if (orderDetailList != null) {
            for (OrderDetailEntity item : orderDetailList) {
                content += item.getProductName() + "    số lượng :" + item.getQuantity() + "Gía :" + item.getPrice() + "\n";
            }
        } else {
            content += "Không có chi tiết đơn hàng.";
        }

        content += "Sản phẩm được đặt bởi: " + order.getUserEntity().getName()+"dễ thương"+"\n";
        content +="Ngày đặt :" +order.getOrderDate();
        content +="Địa chỉ nhận hàng" + order.getDeliveryAddress();
        content +="Số điện thoại ngời nhận" +order.getUserEntity().getPhone();
        content +="Cảm ơn các con nợ";
        mail.setMailContent(content);
        sendMail(mail);

    }
}
