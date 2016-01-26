package com.registration.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
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
        verificationUrl.append("/users/").append(verificationCode).append("/verify");

        return verificationUrl.toString();
    }

}
