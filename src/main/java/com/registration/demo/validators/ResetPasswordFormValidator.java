package com.registration.demo.validators;

import com.registration.demo.datamodel.dto.ResetPasswordForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;

@Component
public class ResetPasswordFormValidator extends LocalValidatorFactoryBean {

    public ResetPasswordFormValidator() {
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

        ResetPasswordForm resetPasswordForm = (ResetPasswordForm) target;

        String newPassword = resetPasswordForm.getPassword();
        String retypedPassword = resetPasswordForm.getRetypePassword();

        if(!newPassword.equals(retypedPassword)){
            errors.reject("validation.message.passwordsAreNotEqual");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ResetPasswordForm.class);
    }
}