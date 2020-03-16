package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.exceptions.DuplicateEntityException;
import com.telerik.ridepalplaylistgenerator.models.Playlist;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistDto;
import com.telerik.ridepalplaylistgenerator.models.dto.UserDto;
import com.telerik.ridepalplaylistgenerator.repository.UserRepository;
import com.telerik.ridepalplaylistgenerator.service.interfaces.PlaylistService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private UserDetailsManager userDetailsManager;
    private PlaylistService playlistService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder encoder,
                           UserDetailsManager userDetailsManager,PlaylistService playlistService){
        this.userRepository = userRepository;
        this.encoder=encoder;
        this.userDetailsManager=userDetailsManager;
        this.playlistService=playlistService;
    }
    @Override
    public void createUser(User user) {
        if (checkUsernameExists(user.getUsername())) {
            throw new DuplicateEntityException(
                    String.format(USERNAME_EXISTS_MESSAGE, user.getUsername()));
        }
        if (checkEmailExists(user.getEmail())) {
            throw new DuplicateEntityException(
                    String.format(EMAIL_EXISTS_MESSAGE, user.getEmail()));
        }
        org.springframework.security.core.userdetails.User newUser=getUser(user);
        userDetailsManager.createUser(newUser);

    }

    private org.springframework.security.core.userdetails.User getUser(User user) {
        List<GrantedAuthority> authorities= AuthorityUtils.createAuthorityList("USER");
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                encoder.encode(user.getPassword()),
                authorities);
    }

    public boolean checkEmailExists(String email) {
        return userRepository.getByEmail(email).size() != 0;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        return userRepository.getByUsername(username).size() != 0;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getUserByUserId(Integer id) {
        return userRepository.getUserByUserId(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(int id, User user) {
        if (checkEmailExists(user.getEmail())) {
            if(!(user.getUserId() == userRepository.getByEmail(user.getEmail()).get(0).getUserId())){
                throw new DuplicateEntityException(String.format(EMAIL_EXISTS_MESSAGE, user.getEmail()));
            }
        }
        if (checkUsernameExists(user.getUsername())) {
            if(!(user.getUserId() == userRepository.getByUsername(user.getUsername()).get(0).getUserId())) {
                throw new DuplicateEntityException(String.format(USERNAME_EXISTS_MESSAGE, user.getUsername()));
            }
        }
        User userToUpdate = getUserByUserId(id);
        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getFirstName() != null) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userToUpdate.setLastName(user.getLastName());
        }
        if (user.getPicture() != null) {
            userToUpdate.setPicture(user.getPicture());
        }
        if(user.getPassword().length() == 0){
            throw new IllegalArgumentException(NOT_EMPTY_MESSAGE);
        }
        userRepository.save(userToUpdate);
        return userToUpdate;
    }


    @Override
    public void deleteByUserId(int id) {
        User userToUpdate = getUserByUserId(id);
        userToUpdate.setEnabled(false);
        userRepository.save(userToUpdate);
    }

    @Override
    public void changeRole(User user) {
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ADMIN");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        encoder.encode(user.getPassword()),
                        authorities);
        userDetailsManager.updateUser(newUser);
        userRepository.save(user);
    }

    @Override
    public List<PlaylistDto> getPlaylistsOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<PlaylistDto> result = new ArrayList<>();
        String username = authentication.getName();
        User user = userRepository.getUserByUsername(username);
        List<Playlist> playlists = playlistService.getAll();
        for (Playlist playlist : playlists) {
            if (playlist.getUserId().equals(user.getUserId())) {
                PlaylistDto temporary = new PlaylistDto();
                result.add(temporary);
            }
        }
        return result;
    }
}