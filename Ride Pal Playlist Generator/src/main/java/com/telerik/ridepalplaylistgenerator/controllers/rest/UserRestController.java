package com.telerik.ridepalplaylistgenerator.controllers.rest;

import com.telerik.ridepalplaylistgenerator.exceptions.DuplicateEntityException;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.DtoMapper;
import com.telerik.ridepalplaylistgenerator.models.dto.UserDto;
import com.telerik.ridepalplaylistgenerator.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private UserService userService;
    private DtoMapper dtoMapper;

    @Autowired
    public UserRestController(UserService userService, DtoMapper dtoMapper) {
        this.userService = userService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public void createUser(@RequestBody @Valid UserDto userDto) {
        try {
            User user = dtoMapper.fromUserDto(userDto);
            userService.createUser(user);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        try {
            return userService.getUserByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException dee) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, dee.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable int id) {
        try {
            return userService.getUserByUserId(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException dee) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, dee.getMessage());
        }
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody @Valid User user) {
        try {
            return userService.updateUser(id,user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException dee) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, dee.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteByUserId(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
