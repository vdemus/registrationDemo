package com.registration.demo.validators;

import com.registration.demo.datamodel.dto.RestorePasswordForm;
import com.registration.demo.persistence.entity.User;
import com.registration.demo.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;

@Component
public class RestorePasswordFormValidator extends LocalValidatorFactoryBean {

    private UserRepository userRepository;

    @Autowired
    public RestorePasswordFormValidator(UserRepository userRepository) {
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

        RestorePasswordForm restorePasswordForm = (RestorePasswordForm) target;
        User user = userRepository.findByEmail(restorePasswordForm.getEmail());

        if (user == null) {
            errors.rejectValue("email", "validation.message.emailIsNotRegistered");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RestorePasswordForm.class);
    }
}