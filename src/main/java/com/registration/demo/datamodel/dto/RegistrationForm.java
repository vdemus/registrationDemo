package com.registration.demo.datamodel.dto;

import com.registration.demo.persistence.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationForm {
    @NotNull
    @Size(min = User.EMAIL_LENGTH_MIN, max = User.EMAIL_LENGTH_MAX, message = "{emailSizeError}")
    @Pattern(regexp=User.EMAIL_PATTERN, message = "{emailPatternError}")
    private String email;

    @NotNull
    @Size(min = User.NAME_LENGTH_MIN, max = User.NAME_LENGTH_MAX, message = "{nameSizeError}")
    private String name;

    @NotNull
    @Size(min = User.PASSWORD_MIN_LENGTH, max = User.PASSWORD_MAX_LENGTH, message = "{passwordSizeError}")
    private String password;

    public RegistrationForm() {
    }

    public RegistrationForm(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
