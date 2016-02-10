package com.registration.demo.controller;

import com.registration.demo.datamodel.dto.RegistrationForm;
import com.registration.demo.datamodel.dto.ResetPasswordForm;
import com.registration.demo.datamodel.dto.RestorePasswordForm;
import com.registration.demo.persistence.entity.User;
import com.registration.demo.service.UserService;
import com.registration.demo.utils.ServerUtils;
import com.registration.demo.validators.RegistrationFormValidator;
import com.registration.demo.validators.ResetPasswordFormValidator;
import com.registration.demo.validators.RestorePasswordFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;
    private RegistrationFormValidator registrationFormValidator;
    private RestorePasswordFormValidator restorePasswordFormValidator;
    private ResetPasswordFormValidator resetPasswordFormValidator;

    @Autowired
    public AuthController(UserService userService,
                          RegistrationFormValidator registrationFormValidator,
                          RestorePasswordFormValidator restorePasswordFormValidator,
                          ResetPasswordFormValidator resetPasswordFormValidator) {
        this.userService = userService;
        this.registrationFormValidator = registrationFormValidator;
        this.restorePasswordFormValidator = restorePasswordFormValidator;
        this.resetPasswordFormValidator = resetPasswordFormValidator;
    }

    @InitBinder("registrationForm")
    protected void initRegistrationFormBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(registrationFormValidator);
    }

    @InitBinder("restorePasswordForm")
    protected void initRestorePasswordFormBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(restorePasswordFormValidator);
    }

    @InitBinder("resetPasswordForm")
    protected void initResetPasswordFormBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(resetPasswordFormValidator);
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

        ServerUtils.setFlashAttributes(redirectAttributes, "success", "auth.registrationSuccessful");

        return "redirect:/";
    }

    @RequestMapping(value = "/{verificationCode}/verify", method = RequestMethod.GET)
    public String verifyRegistration(@PathVariable("verificationCode") String verificationCode,
                                     RedirectAttributes redirectAttributes,
                                     HttpServletRequest request) throws ServletException{
        userService.verify(verificationCode);

        ServerUtils.setFlashAttributes(redirectAttributes, "success", "auth.verificationSuccessful");

        request.logout();
        return "redirect:/";
    }

    @RequestMapping(value = "/restore-password", method = RequestMethod.GET)
    public String restorePassword(Model model) {
        model.addAttribute("restorePasswordForm",new RestorePasswordForm());
        return "restore-password";
    }

    @RequestMapping(value = "/restore-password", method = RequestMethod.POST)
    public String restorePassword(@ModelAttribute("restorePasswordForm") @Valid RestorePasswordForm restorePasswordForm,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        LOGGER.debug(restorePasswordForm.toString());

        if (result.hasErrors()) {
            LOGGER.debug("restore password form has errors");
            return "restore-password";
        }

        userService.restorePassword(restorePasswordForm.getEmail());

        ServerUtils.setFlashAttributes(redirectAttributes, "info", "auth.restorePassword.mailIsSent");

        return "redirect:/";
    }

    @RequestMapping(value = "/restore-password/{passwordRestoreCode}", method = RequestMethod.GET)
    public String resetPassword(Model model) throws ServletException{
        model.addAttribute("resetPasswordForm", new ResetPasswordForm());
        return "reset-password";
    }

    @RequestMapping(value = "/restore-password/{passwordRestoreCode}", method = RequestMethod.POST)
    public String resetPassword(@PathVariable("passwordRestoreCode") String passwordRestoreCode,
                                @ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) throws ServletException {

        if (result.hasErrors()) {
            LOGGER.debug("reset password form has errors");
            return "reset-password";
        }

        userService.resetPassword(passwordRestoreCode, resetPasswordForm.getPassword());

        ServerUtils.setFlashAttributes(redirectAttributes, "success", "auth.passwordResetSuccessfull");

        return "redirect:/";
    }


}
