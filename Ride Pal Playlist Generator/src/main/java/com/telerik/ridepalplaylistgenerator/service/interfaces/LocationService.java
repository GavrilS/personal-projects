package com.telerik.ridepalplaylistgenerator.service.interfaces;

import org.json.JSONException;

public interface LocationService {
    double getTravelDuration(String from, String to) throws JSONException;
}
