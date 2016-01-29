package com.registration.demo.service.impl;


import com.registration.demo.datamodel.dto.UserDetailsImpl;
import com.registration.demo.persistence.entity.User;
import com.registration.demo.persistence.repositories.UserRepository;
import com.registration.demo.service.MailService;
import com.registration.demo.service.UserService;
import com.registration.demo.utils.ServerUtils;
import com.registration.demo.utils.UserRole;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void register(User user) {
        LOGGER.debug("Registering {}", user.toString());

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.getRoles().add(UserRole.UNVERIFIED);

        String verificationCode = RandomStringUtils.randomAlphanumeric(User.VERIFICATION_CODE_LENGTH);
        user.setVerificationCode(verificationCode);

        userRepository.save(user);

        try {
            mailService.sendConfirmation(user.getEmail(), user.getName(), verificationCode);
        } catch (MessagingException e) {
            LOGGER.error("Error during sending confirmation email to user: {}\n{}",user.getEmail(), e );
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void verify(String verificationCode) {
        long loggedInUserId = ServerUtils.getSessionUser().getId();
        User user = userRepository.findOne(loggedInUserId);

        ServerUtils.validate(user.getRoles().contains(UserRole.UNVERIFIED), "validation.message.alreadyVerified");
        ServerUtils.validate(user.getVerificationCode().equals(verificationCode), "validation.message.incorrectVerificationCode");

        user.getRoles().remove(UserRole.UNVERIFIED);
        user.setVerificationCode(null);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserDetailsImpl(user);
    }

    @Override
    public void resendConfirmationMail(long loggedInUserId) {
        User user = userRepository.findOne(loggedInUserId);

        ServerUtils.validate(user.getRoles().contains(UserRole.UNVERIFIED), "validation.message.alreadyVerified");

        String verificationCode = RandomStringUtils.randomAlphanumeric(User.VERIFICATION_CODE_LENGTH);
        user.setVerificationCode(verificationCode);

        userRepository.save(user);

        try {
            mailService.sendConfirmation(user.getEmail(), user.getName(), verificationCode);
        } catch (MessagingException e) {
            LOGGER.error("Error during sending confirmation email to user: {}\n{}",user.getEmail(), e );
        }
    }
}
