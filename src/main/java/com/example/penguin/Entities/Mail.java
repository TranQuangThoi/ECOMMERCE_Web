package com.example.penguin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    private String mailFrom;
    private String mailTo;
    private String mailSubject;
    private String mailContent;
    private String contentType = "text/plain";
    private List<Object> attachments;

}
