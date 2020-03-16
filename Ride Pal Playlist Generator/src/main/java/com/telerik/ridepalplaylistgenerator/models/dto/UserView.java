package com.telerik.ridepalplaylistgenerator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserView {
    private int id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String picture;

    private int age;
}
