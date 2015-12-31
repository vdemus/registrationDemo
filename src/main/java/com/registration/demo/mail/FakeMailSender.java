package com.registration.demo.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@Profile("dev")
public class FakeMailSender implements MailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeMailSender.class);

    @Override
    public void send(String recipient, String subject, String body) throws MessagingException {
        LOGGER.debug("Send email to {}", recipient);
        LOGGER.debug("subject:{}", subject);
        LOGGER.debug("body:{}", body);
    }
}
