package com.registration.demo.controller;

import com.registration.demo.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailController {

    private MailSender mailSender;

    @Autowired
    public MailController(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping(path = "/send-mail")
    public String sendEmail() throws MessagingException{
        mailSender.send("example@mail.com", "Hello Spring Boot!", "Mail sent from applicaton");
        return "Mail is sent";
    }
}
