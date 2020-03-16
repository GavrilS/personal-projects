package com.telerik.ridepalplaylistgenerator.models.dto;

import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    private UserRepository userRepository;

    @Autowired
    public DtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User fromUserDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
