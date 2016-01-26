package com.registration.demo.service;

import javax.mail.MessagingException;

public interface MailService {
    void send(String userEmail, String subject, String body) throws MessagingException;
    void sendConfirmation(String userEmail, String userName, String verificationCode) throws MessagingException;
}
