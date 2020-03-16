package com.telerik.ridepalplaylistgenerator.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.telerik.ridepalplaylistgenerator.models.Artist;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistList {

    @JsonProperty("data")
    private List<Artist> listOfArtists;

    public ArtistList() {
        listOfArtists = new ArrayList<>();
    }

    public List<Artist> getListOfArtists() {
        return listOfArtists;
    }

    public void setListOfArtists(List<Artist> listOfArtists) {
        this.listOfArtists = listOfArtists;
    }
}
