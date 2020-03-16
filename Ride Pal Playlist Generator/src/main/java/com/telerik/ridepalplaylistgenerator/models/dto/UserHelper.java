package com.telerik.ridepalplaylistgenerator.models.dto;

import com.telerik.ridepalplaylistgenerator.models.User;

public class UserHelper {
    public static User updateUserDetails(User user, UserView userView){
        user.setEmail(userView.getEmail());
        user.setFirstName(userView.getFirstName());
        user.setLastName(userView.getLastName());
        user.setPicture(userView.getPicture());
        user.setAge(userView.getAge());

        return user;
    }
}
