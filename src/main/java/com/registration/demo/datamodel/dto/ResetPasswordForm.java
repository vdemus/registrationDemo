package com.registration.demo.datamodel.dto;

import com.registration.demo.persistence.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResetPasswordForm {

    @NotNull
    @Size(min = User.PASSWORD_MIN_LENGTH, max = User.PASSWORD_MAX_LENGTH, message = "{passwordSizeError}")
    private String password;

    @NotNull
    @Size(min = User.PASSWORD_MIN_LENGTH, max = User.PASSWORD_MAX_LENGTH, message = "{passwordSizeError}")
    private String retypePassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }
}
