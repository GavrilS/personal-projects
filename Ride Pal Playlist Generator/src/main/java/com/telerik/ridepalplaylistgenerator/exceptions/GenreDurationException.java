package com.telerik.ridepalplaylistgenerator.exceptions;

public class GenreDurationException extends RuntimeException {
    private static final String GENRE_DURATION_ERROR_MSG = "The sum of the durations of all genres used to generate the" +
            " playlist must be equal to 100.";

    public GenreDurationException(){
        super(GENRE_DURATION_ERROR_MSG);
    }
}
