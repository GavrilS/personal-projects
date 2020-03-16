package com.telerik.ridepalplaylistgenerator.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.telerik.ridepalplaylistgenerator.models.Genre;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreList {
    @JsonProperty("data")
    private List<Genre> listOfGenres;

    public GenreList() {
        listOfGenres = new ArrayList<>();
    }

    public List<Genre> getListOfGenres() {
        return listOfGenres;
    }

    public void setListOfGenres(List<Genre> listOfGenres) {
        this.listOfGenres = listOfGenres;
    }
}
