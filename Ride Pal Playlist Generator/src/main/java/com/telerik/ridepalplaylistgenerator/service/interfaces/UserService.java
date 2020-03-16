package com.telerik.ridepalplaylistgenerator.service.interfaces;

import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistDto;
import com.telerik.ridepalplaylistgenerator.models.dto.UserDto;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);

    User getUserByUserId(Integer id);

    void deleteByUserId(int id);

    List<User> getAll();

    void createUser(User user);

    User updateUser(int id, User user);

    void changeRole(User user);

    boolean checkUsernameExists(String username);

    List<PlaylistDto> getPlaylistsOfUser();
}
