package com.registration.demo.datamodel.dto;

import com.registration.demo.persistence.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RestorePasswordForm {

    @NotNull
    @Size(min = User.EMAIL_LENGTH_MIN, max = User.EMAIL_LENGTH_MAX, message = "{emailSizeError}")
    @Pattern(regexp=User.EMAIL_PATTERN, message = "{emailPatternError}")
    private String email;

    public RestorePasswordForm() {
    }

    public RestorePasswordForm(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RestorePasswordForm{" +
                "email='" + email + '\'' +
                '}';
    }
}
