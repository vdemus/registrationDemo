package com.registration.demo.utils;

import com.registration.demo.datamodel.dto.UserDetailsImpl;
import com.registration.demo.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Component
public class ServerUtils {

    private static MessageSource messageSource;


    private static String baseUrl;

    @Autowired
    public ServerUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Value("${hostAndPort}")
    public void setBaseUrl(String baseUrl) {
        ServerUtils.baseUrl = baseUrl;
    }

    public static void setFlashAttributes(RedirectAttributes redirectAttributes, String status, String messageKey) {
        redirectAttributes.addFlashAttribute("flashKind", status);
        redirectAttributes.addFlashAttribute("flashMessage", getMessageByKey(messageKey));
    }

    public static String getMessageByKey(String key, Object... args) {
        return messageSource.getMessage(key, args, Locale.getDefault());
    }

    public static String generateVerificationUrl(String verificationCode) {
        StringBuilder verificationUrl = new StringBuilder(baseUrl);
        verificationUrl.append("/auth/").append(verificationCode).append("/verify");

        return verificationUrl.toString();
    }

    public static String generatePasswordRestoreUrl(String passwordRestoreCode) {
        StringBuilder passwordRestoreUrl = new StringBuilder(baseUrl);
        passwordRestoreUrl.append("/auth/restore-password/").append(passwordRestoreCode);

        return passwordRestoreUrl.toString();
    }

    public static void validate(boolean validationCondition, String errorMessageKey, Object... args) {
        if (!validationCondition) {
            throw new RuntimeException(getMessageByKey(errorMessageKey, args));
        }
    }

    public static User getSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) principal).getUser();
        }

        return null;
    }
}
