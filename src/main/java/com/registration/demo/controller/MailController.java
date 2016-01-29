package com.registration.demo.controller;

import com.registration.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/mail")
public class MailController {

    private MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(path = "/send-mail")
    @ResponseBody
    public String sendEmail() throws MessagingException{
        mailService.send("example@mail.com", "Hello Spring Boot!", "Mail sent from applicaton");
        return "Mail is sent";
    }
}
