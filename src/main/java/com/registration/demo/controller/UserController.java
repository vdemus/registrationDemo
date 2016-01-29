package com.registration.demo.controller;

import com.registration.demo.service.UserService;
import com.registration.demo.utils.ServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/resend-confirmation-email", method = RequestMethod.GET)
    public String resendConfirmationMail(RedirectAttributes redirectAttributes) {

        long loggedInUserId = ServerUtils.getSessionUser().getId();

        userService.resendConfirmationMail(loggedInUserId);

        ServerUtils.setFlashAttributes(redirectAttributes, "success", "auth.confirmationMailIsSent");

        return "redirect:/home";
    }


}
