package com.registration.demo.service.impl;


import com.registration.demo.persistence.entity.User;
import com.registration.demo.datamodel.dto.RegistrationForm;
import com.registration.demo.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements com.registration.demo.service.UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegistrationForm registrationForm) {
        User user = new User(
                registrationForm.getName(),
                registrationForm.getEmail(),
                registrationForm.getPassword());

        userRepository.save(user);
    }
}
