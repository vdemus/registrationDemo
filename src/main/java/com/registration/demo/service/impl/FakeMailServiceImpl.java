package com.registration.demo.service.impl;

import com.registration.demo.service.MailService;
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
    public void send(String recipient, String subject, String body) throws MessagingException {
        LOGGER.debug("Send email to {}", recipient);
        LOGGER.debug("subject:{}", subject);
        LOGGER.debug("body:{}", body);
    }
}
