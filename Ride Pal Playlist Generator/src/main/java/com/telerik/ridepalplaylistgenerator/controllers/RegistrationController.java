package com.telerik.ridepalplaylistgenerator.controllers;

import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import java.util.List;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.PASSWORD_ERROR;
import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.USERNAME_ALREADY_EXISTS;


@Controller
public class RegistrationController {
    private UserService userService;
    private UserDetailsManager userDetailsManager;
    private PasswordEncoder encoder;


    @Autowired
    public RegistrationController(UserService userService, UserDetailsManager userDetailsManager,
                                  PasswordEncoder encoder) {
        this.userService = userService;
        this.userDetailsManager = userDetailsManager;
        this.encoder=encoder;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String error = "";
            if (bindingResult.getFieldError() != null) {
                error = bindingResult.getFieldError().getDefaultMessage();
            } else if (bindingResult.getGlobalError() != null) {
                error = bindingResult.getGlobalError().getDefaultMessage()
                        .replace(bindingResult.getGlobalError().getDefaultMessage(), PASSWORD_ERROR);
            }

            model.addAttribute("error", error);
            return "register";
        }
        if (userDetailsManager.userExists(user.getUsername())) {
            model.addAttribute("error", USERNAME_ALREADY_EXISTS);
            return "register";
        }
        userService.createUser(user);
        return "register-confirmation";
    }

    @GetMapping("/register-confirmation")
    public String showRegisterConfirmation() {
        return "register-confirmation";
    }
}
