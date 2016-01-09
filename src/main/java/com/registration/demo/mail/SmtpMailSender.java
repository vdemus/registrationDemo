package com.registration.demo.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Profile("prod")
public class SmtpMailSender implements MailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpMailSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void send(String recipient, String subject, String body) throws MessagingException{
        LOGGER.debug("SmtpMailSender");
        LOGGER.debug("Send email to {}", recipient);
        LOGGER.debug("subject:{}", subject);
        LOGGER.debug("body:{}", body);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipient);
        helper.setFrom(sender);
        helper.setSubject(subject);
        helper.setText(body, true);

        javaMailSender.send(message);
    }
}