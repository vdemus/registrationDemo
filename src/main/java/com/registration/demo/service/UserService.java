package com.registration.demo.service;

import com.registration.demo.datamodel.dto.RegistrationForm;
import com.registration.demo.persistence.entity.User;

public interface UserService {
    void register(User user);
}
