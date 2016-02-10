package com.registration.demo.persistence.entity;

import com.registration.demo.utils.ServerUtils;
import com.registration.demo.utils.UserRole;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS", indexes = {
        @Index(columnList = "email", unique = true)
})
public class User {

    public static final int NAME_LENGTH_MAX = 100;
    public static final int NAME_LENGTH_MIN = 1;
    public static final int EMAIL_LENGTH_MAX = 250;
    public static final int EMAIL_LENGTH_MIN = 5;
    public static final int VERIFICATION_CODE_LENGTH = 16;
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 30;
    public static final String EMAIL_PATTERN = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = NAME_LENGTH_MAX)
    private String name;

    @Column(nullable = false, length = EMAIL_LENGTH_MAX)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<UserRole>();

    @Column(length = VERIFICATION_CODE_LENGTH)
    private String verificationCode;

    @Column(length = VERIFICATION_CODE_LENGTH)
    private String passwordRestoreCode;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getPasswordRestoreCode() {
        return passwordRestoreCode;
    }

    public void setPasswordRestoreCode(String passwordRestoreCode) {
        this.passwordRestoreCode = passwordRestoreCode;
    }

    public boolean isAdmin() {
        return getRoles().contains(UserRole.ADMIN);
    }

    public boolean isEditableInCurrentSession() {
        User loggedInUser = ServerUtils.getSessionUser();

        if (loggedInUser == null) return false;

        return (loggedInUser.getId().equals(this.id)) || (loggedInUser.isAdmin());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
