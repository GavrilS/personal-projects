package com.telerik.ridepalplaylistgenerator.exceptions;

public class TravelDurationException extends RuntimeException {
    private static final String TRAVEL_DURATION_ERROR_MSG = "The travel duration is too big, the playlist generator" +
            " cannot fill the time. Try to generate more than 1 playlist for the duration of the travel using intermediate" +
            " points.";

    public TravelDurationException(){
        super(TRAVEL_DURATION_ERROR_MSG);
    }
}
