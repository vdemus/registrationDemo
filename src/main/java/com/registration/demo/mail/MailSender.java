package com.registration.demo.mail;

import javax.mail.MessagingException;

public interface MailSender {
    void send(String recipient, String subject, String body) throws MessagingException;
}
