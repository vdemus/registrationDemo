package com.registration.demo.service.impl;

import com.registration.demo.service.MailService;
import com.registration.demo.utils.ServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Profile("prod")
public class SmtpMailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpMailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void send(String userEmail, String subject, String body) throws MessagingException{
        LOGGER.debug("Send email to {}", userEmail);
        LOGGER.debug("subject:{}", subject);
        LOGGER.debug("body:{}", body);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(userEmail);
        helper.setFrom(sender);
        helper.setSubject(subject);
        helper.setText(body, true);

        javaMailSender.send(message);
    }

    @Override
    public void sendConfirmation(String email, String userName, String verificationCode) throws MessagingException {
        LOGGER.debug("Send email to {}", email);
        LOGGER.debug("userName:{}", userName);
        LOGGER.debug("verificationCode:{}", verificationCode);

        String subject = ServerUtils.getMessageByKey("mail.verification.subject");

        String verificationUrl = ServerUtils.generateVerificationUrl(verificationCode);
        String mailBody = ServerUtils.getMessageByKey("mail.verification.body", userName, verificationUrl);

        send(email, subject, mailBody);
    }

    @Override
    public void sendPasswordRestore(String email, String userName, String passwordRestoreCode) throws MessagingException {
        LOGGER.debug("Send email to {}", email);
        LOGGER.debug("userName:{}", userName);
        LOGGER.debug("passwordRestoreCode:{}", passwordRestoreCode);

        String subject = ServerUtils.getMessageByKey("mail.restore.subject");

        String passwordRestoreUrl = ServerUtils.generatePasswordRestoreUrl(passwordRestoreCode);
        String mailBody = ServerUtils.getMessageByKey("mail.restore.body", userName, passwordRestoreUrl);

        send(email, subject, mailBody);
    }
}
