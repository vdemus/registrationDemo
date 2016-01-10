package com.registration.demo.service;

import javax.mail.MessagingException;

public interface MailService {
    void send(String recipient, String subject, String body) throws MessagingException;
}
