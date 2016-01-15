package com.registration.demo.controller;

import com.registration.demo.datamodel.dto.RegistrationForm;
import com.registration.demo.persistence.entity.User;
import com.registration.demo.service.UserService;
import com.registration.demo.utils.ResponseUtils;
import com.registration.demo.validators.RegistrationFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;
    private RegistrationFormValidator registrationFormValidator;

    @Autowired
    public AuthController(UserService userService,
                          RegistrationFormValidator registrationFormValidator) {
        this.userService = userService;
        this.registrationFormValidator = registrationFormValidator;
    }

    @InitBinder("registrationForm")
    protected void initRegistrationFormBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(registrationFormValidator);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registrationForm",new RegistrationForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        LOGGER.debug(registrationForm.toString());

        if (result.hasErrors()) {
            LOGGER.debug("Registration form has errors");
            return "register";
        }

        User user = new User(
                registrationForm.getName(),
                registrationForm.getEmail(),
                registrationForm.getPassword());

        userService.register(user);

        ResponseUtils.setFlashAttributes(redirectAttributes, "success", "registrationSuccessful");

        return "redirect:/home";
    }
}
