package com.telerik.ridepalplaylistgenerator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET enabled = true WHERE user_id = ?")
@Where(clause = "enabled <> 0")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    @Size(min = FIRST_NAME_MIN_LENGTH,
            max = FIRST_NAME_MAX_LENGTH,
            message = USER_FIRST_NAME_INVALID_NAME_LENGTH_ERROR_MSG)
    private String firstName;

    @Column(name = "last_name")
    @Size(min = LAST_NAME_MIN_LENGTH,
            max = LAST_NAME_MAX_LENGTH,
            message = USER_LAST_NAME_INVALID_NAME_LENGTH_ERROR_MSG)
    private String lastName;

    @Column(name="email")
    @Size(min = USER_EMAIL_MIN_LENGTH,
            max = USER_EMAIL_MAX_LENGTH,
            message = USER_EMAIL_INVALID_ERROR_MSG)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "age")
    private int age=0;

    @Column(name="picture")
    private String picture;

    @Column(name="enabled")
    private boolean enabled=true;

    @Transient
    private String passwordConfirmation;
}
