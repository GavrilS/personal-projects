package com.telerik.ridepalplaylistgenerator.controllers;

import com.telerik.ridepalplaylistgenerator.exceptions.DuplicateEntityException;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.UserHelper;
import com.telerik.ridepalplaylistgenerator.models.dto.UserView;
import com.telerik.ridepalplaylistgenerator.service.interfaces.PlaylistService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.UserService;
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
public class AdminController {
    private UserService userService;
    private PlaylistService playlistService;

    public AdminController(UserService userService, PlaylistService playlistService) {
        this.userService = userService;
        this.playlistService = playlistService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model){
        model.addAttribute("playlists",playlistService.getAll());
        model.addAttribute("userAdmin",userService.getAll());
        return "admin";
    }

    @GetMapping("/admin/{username}/edit")
    @PreAuthorize("authentication.principal.username ==hasAuthority('ADMIN')")
    public String showUpdateForm(@PathVariable String username, Model model) {
        try {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
            return "update-user";
        } catch (EntityNotFoundException enfe) {
            return "error";
        }
    }

    @PostMapping("/admin/{username}/update")
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
            return showAllUsers(model);
        } catch (DuplicateEntityException dee) {
            return "error";
        } catch (EntityNotFoundException enfe) {
            return "error";
        }
    }

    @PostMapping("/admin/delete-user")
    @PreAuthorize("authentication.principal.username == hasAuthority('ADMIN')")
    public String deleteUser(@RequestParam(name = "userId", required = false) Integer userId) {
        try {
            userService.deleteByUserId(userId);
            return "user-delete-success";
        } catch (
                EntityNotFoundException enfe) {
            return "error";
        }
    }
}
