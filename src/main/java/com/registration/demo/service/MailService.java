package com.registration.demo.service;

import javax.mail.MessagingException;

public interface MailService {
    void send(String userEmail, String subject, String body) throws MessagingException;
    void sendConfirmation(String email, String userName, String verificationCode) throws MessagingException;
    void sendPasswordRestore(String email, String userName, String passwordRestoreCode) throws MessagingException;
}
