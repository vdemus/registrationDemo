package com.registration.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Component
public class ResponseUtils {

    private static MessageSource messageSource;

    @Autowired
    public ResponseUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static void setFlashAttributes(RedirectAttributes redirectAttributes, String status, String messageKey) {
        redirectAttributes.addFlashAttribute("flashKind", status);
        redirectAttributes.addFlashAttribute("flashMessage", getMessageByKey(messageKey));
    }

    private static String getMessageByKey(String key, Object... args) {
        return messageSource.getMessage(key, args, Locale.getDefault());
    }

}
