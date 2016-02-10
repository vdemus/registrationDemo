package com.registration.demo.controller;

import com.registration.demo.datamodel.dto.UserEditForm;
import com.registration.demo.persistence.entity.User;
import com.registration.demo.service.UserService;
import com.registration.demo.utils.ServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

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

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String getUserInformation(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "user-information";
    }

    @RequestMapping(value = "/{userId}/edit", method = RequestMethod.GET)
    public String editUserInformation(@PathVariable("userId") long userId, Model model) {
        User user = userService.getUserById(userId);
        UserEditForm userEditForm = new UserEditForm(user.getName(),user.getEmail());
        model.addAttribute("userEditForm", userEditForm);
        return "user-edit";
    }

    @RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
    public String updateUserInformation(@PathVariable("userId") long userId,
                                      @ModelAttribute("userEditForm") @Valid UserEditForm userEditForm,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "user-edit";
        }

        StringBuilder redirectUrl = new StringBuilder("redirect:/users/").append(userId);

        User user = new User();
        user.setId(userId);
        user.setName(userEditForm.getName());

        userService.updateUser(user);

        ServerUtils.setFlashAttributes(redirectAttributes, "info", "auth.profileUpdatedSuccessfully");

        return redirectUrl.toString();
    }


}
