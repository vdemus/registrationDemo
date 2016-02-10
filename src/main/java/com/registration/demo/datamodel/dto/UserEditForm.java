package com.registration.demo.datamodel.dto;

import com.registration.demo.persistence.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserEditForm {

    @NotNull
    @Size(min = User.NAME_LENGTH_MIN, max = User.NAME_LENGTH_MAX, message = "{nameSizeError}")
    private String name;

//    @NotNull
//    @Size(min = User.EMAIL_LENGTH_MIN, max = User.EMAIL_LENGTH_MAX, message = "{emailSizeError}")
//    @Pattern(regexp=User.EMAIL_PATTERN, message = "{emailPatternError}")
    private String email;

    public UserEditForm() {
    }

    public UserEditForm(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEditForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
