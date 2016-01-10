package com.registration.demo.datamodel.dto;

import com.registration.demo.datamodel.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationForm {
    @NotNull
    @Size(min = User.EMAIL_LENGTH_MIN, max = User.EMAIL_LENGTH_MAX)
    @Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "{emailPatternError}")
    private String email;

    @NotNull
    @Size(min = User.NAME_LENGTH_MIN, max = User.NAME_LENGTH_MAX)
    private String name;

    @NotNull
    @Size(min = 6, max = 30, message = "{nameSizeError}")
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
