package com.registration.demo.service.impl;

import com.registration.demo.service.MailService;
import com.registration.demo.utils.ServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Profile("dev")
public class FakeMailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeMailServiceImpl.class);

    @Override
    public void send(String userEmail, String subject, String body) throws MessagingException {
        LOGGER.debug("Send email to {}", userEmail);
        LOGGER.debug("subject:{}", subject);
        LOGGER.debug("body:{}", body);
    }

    @Override
    public void sendConfirmation(String userEmail, String userName, String verificationCode) throws MessagingException {
        LOGGER.debug("Send email to {}", userEmail);
        LOGGER.debug("userName:{}", userName);
        LOGGER.debug("verificationCode:{}", verificationCode);

        String subject = ServerUtils.getMessageByKey("mail.verification.subject");
        LOGGER.debug("subject:{}", subject);

        String verificationUrl = ServerUtils.generateVerificationUrl(verificationCode);
        LOGGER.debug("verificationUrl:{}", verificationUrl);

        String mailBody = ServerUtils.getMessageByKey("mail.verification.body", userName, verificationUrl);
        LOGGER.debug("mailBody:{}", mailBody);
    }
}
