package com.telerik.ridepalplaylistgenerator.models.dto;

import com.telerik.ridepalplaylistgenerator.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    @NotNull
    @NotBlank
    @Size(min = USERNAME_NAME_MIN_LENGTH,
            max = USERNAME_NAME_MAX_LENGTH,
            message = USERNAME_INVALID_NAME_LENGTH_ERROR_MSG)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = USER_PASSWORD_MIN_LENGTH,
            max = USER_PASSWORD_MAX_LENGTH,
            message = USER_PASSWORD_INVALID_ERROR_MSG)
    private String password;

    private String passwordConfirmation;

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    public static UserDto fromModel(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setPasswordConfirmation(user.getPasswordConfirmation());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }
}
