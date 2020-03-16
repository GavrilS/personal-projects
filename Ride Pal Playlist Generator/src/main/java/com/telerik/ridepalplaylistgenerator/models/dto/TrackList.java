package com.telerik.ridepalplaylistgenerator.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.telerik.ridepalplaylistgenerator.models.Track;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackList {

    @JsonProperty("data")
    private List<Track> tracks;

    public TrackList() {
        tracks = new ArrayList<>();
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
