package com.telerik.ridepalplaylistgenerator.constants;

public class ValidationHelper {

    //    length message
    public static final int TITLE_NAME_MIN_LENGTH = 2;
    public static final int TITLE_NAME_MAX_LENGTH = 60;
    public static final int DESCRIPTION_MAX_LENGTH = 200;
    public static final int USERNAME_NAME_MIN_LENGTH = 2;
    public static final int USERNAME_NAME_MAX_LENGTH = 60;
    public static final int LAST_NAME_MIN_LENGTH = 2;
    public static final int LAST_NAME_MAX_LENGTH = 60;
    public static final int FIRST_NAME_MIN_LENGTH = 2;
    public static final int FIRST_NAME_MAX_LENGTH = 60;
    public static final int USER_EMAIL_MIN_LENGTH = 6;
    public static final int USER_EMAIL_MAX_LENGTH = 60;
    public static final int USER_PASSWORD_MIN_LENGTH = 6;
    public static final int USER_PASSWORD_MAX_LENGTH = 68;

    //    error message
    public static final String TITLE_INVALID_NAME_LENGTH_ERROR_MSG =
            "The title's length should be between {min} and {max} symbols.";
    public static final String DESCRIPTION_INVALID_LENGTH_ERROR_MSG =
            "The description's length should be between {min} and {max} symbols.";
    public static final String USERNAME_INVALID_NAME_LENGTH_ERROR_MSG =
            "The username's length should be between {min} and {max} symbols.";
    public static final String USER_FIRST_NAME_INVALID_NAME_LENGTH_ERROR_MSG =
            "The first name's length should be between {min} and {max} symbols.";
    public static final String USER_LAST_NAME_INVALID_NAME_LENGTH_ERROR_MSG =
            "The last name's length should be between {min} and {max} symbols.";
    public static final String USER_EMAIL_INVALID_ERROR_MSG =
            "The email's length should be between {min} and {max} symbols.";
    public static final String USER_PASSWORD_INVALID_ERROR_MSG =
            "The password's length should be between {min} and {max} symbols.";
    public static final String USERNAME_EXISTS_MESSAGE = "User with username %s already exists.";
    public static final String EMAIL_EXISTS_MESSAGE = "User with email %s already exists.";
    public static final String NOT_EMPTY_MESSAGE = "Username/password can not be empty!";
    public static final String TITLE_EXISTS_MESSAGE = "Playlist with title %s already exists.";
    public static final String PASSWORD_ERROR="Passwords do not match.";
    public static final String USERNAME_ALREADY_EXISTS="User with this username already exists!";
}
