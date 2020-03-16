package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.components.RestTemplateResponseErrorHandler;
import com.telerik.ridepalplaylistgenerator.service.interfaces.LocationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationServiceImpl implements LocationService {
    private RestTemplate restTemplate;

    @Autowired
    public LocationServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    public LocationServiceImpl() {
        this.restTemplate=new RestTemplate();
    }

    @Override
    public double getTravelDuration(String from, String to) throws JSONException {
        String url="http://dev.virtualearth.net/REST/v1/Routes/driving?wayPoint.1="+from
                +"&waypoint.2="+to+
                "&distanceUnit=km&key=AicTG1qkr40K7KE6I0E6gFah2AoV0N1f_MoBfUaL3Xgn4zEK7DfPbUa8GjbDEM6x";
        String response=restTemplate.getForObject(url,String.class);
        JSONObject jsonObject=new JSONObject(response);
        double duration = jsonObject
                .getJSONArray("resourceSets")
                .getJSONObject(0)
                .getJSONArray("resources")
                .getJSONObject(0).getDouble("travelDuration");
        return duration;
    }
}