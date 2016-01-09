package com.registration.demo.controller;

import com.registration.demo.datamodel.dto.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registrationForm",new RegistrationForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm, BindingResult result) {
        LOGGER.debug(registrationForm.toString());

        if (result.hasErrors()) {
            LOGGER.debug("Registration form has errors");
            return "register";
        }
        return "redirect:/home";
    }

//    @ExceptionHandler(Exception.class)
//    public String handleValidationError() {
//        LOGGER.debug("Registration form has errors");
//        return "register";
//    }
}
