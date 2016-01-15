package com.registration.demo.validators;

import com.registration.demo.datamodel.dto.RegistrationForm;
import com.registration.demo.persistence.entity.User;
import com.registration.demo.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;

@Component
public class RegistrationFormValidator extends LocalValidatorFactoryBean {

    private UserRepository userRepository;

    @Autowired
    public RegistrationFormValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ExecutableValidator forExecutables() {
        return null;
    }

    @Override
    public ParameterNameProvider getParameterNameProvider() {
        return null;
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        super.validate(target, errors, validationHints);

        if (errors.hasErrors()) {
            return;
        }

        RegistrationForm registrationForm = (RegistrationForm) target;
        User user = userRepository.findByEmail(registrationForm.getEmail());

        if (user != null) {
            errors.rejectValue("email", "emailNotUnique");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RegistrationForm.class);
    }
}
