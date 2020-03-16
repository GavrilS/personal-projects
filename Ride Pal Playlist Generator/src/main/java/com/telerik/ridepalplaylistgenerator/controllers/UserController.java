package com.telerik.ridepalplaylistgenerator.controllers;

import com.telerik.ridepalplaylistgenerator.exceptions.DuplicateEntityException;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.DtoMapper;
import com.telerik.ridepalplaylistgenerator.models.dto.UserHelper;
import com.telerik.ridepalplaylistgenerator.models.dto.UserView;
import com.telerik.ridepalplaylistgenerator.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Base64;

@Controller
public class UserController {
    private UserService userService;
    private DtoMapper dtoMapper;

    @Autowired
    public UserController(UserService userService, DtoMapper dtoMapper) {
        this.userService = userService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model, String username) {
        model.addAttribute("user", userService.getUserByUsername(username));
        model.addAttribute("users", userService.getAll());
        model.addAttribute("playlist", userService.getPlaylistsOfUser());
        return "users-all";
    }

    @GetMapping("/users/username/{username}")
    @PreAuthorize("authentication.principal.username == #username or hasAuthority('ADMIN')")
    public String showUserProfile(@PathVariable String username, Model model) {
        try {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
            return "user-profile";
        } catch (SpelEvaluationException see) {
            return "redirect:/access-denied";
        }
    }

    @GetMapping("/users/username/{username}/edit")
    @PreAuthorize("authentication.principal.username == #username or hasAuthority('ADMIN')")
    public String showUpdateForm(@PathVariable String username, Model model) {
        try {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
            return "update-user";
        } catch (EntityNotFoundException enfe) {
            return "error";
        }
    }

    @PostMapping("/users/username/{username}/update")
    public String updateUser(@PathVariable String username, @RequestParam MultipartFile file,
                             @Valid UserView user, BindingResult bindingResult,
                             Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            String errorMsg = "";
            if (bindingResult.getFieldError() != null) {
                errorMsg = bindingResult.getFieldError().getDefaultMessage();
            }
            model.addAttribute("error", errorMsg);
            return "update-user";
        }
        try {
            User userNew = userService.getUserByUsername(user.getUsername());
            UserHelper.updateUserDetails(userNew, user);
            userService.updateUser(userNew.getUserId(), userNew);
            if (!file.isEmpty()) {
                userNew.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
            }
            userService.updateUser(userNew.getUserId(), userNew);
            return showUserProfile(userNew.getUsername(), model);
        } catch (DuplicateEntityException dee) {
            return "error";
        } catch (EntityNotFoundException enfe) {
            return "error";
        }
    }

    @GetMapping("/users/{username}/delete")
    @PreAuthorize("authentication.principal.username == #username or hasAuthority('ADMIN')")
    public String showUserDeletePage(@PathVariable String username, Model model) {
        try {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
            return "user-delete";
        } catch (EntityNotFoundException enfe) {
            return "error";
        }
    }

    @PostMapping("/users/{username}/delete")
    @PreAuthorize("authentication.principal.username == #username or hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable String username, Model model) {
        try {
            User user = userService.getUserByUsername(username);
            userService.deleteByUserId(user.getUserId());
            model.addAttribute("user", user);
            return "user-delete-success";
        } catch (EntityNotFoundException enfe) {
            return "error";
        }
    }
}
