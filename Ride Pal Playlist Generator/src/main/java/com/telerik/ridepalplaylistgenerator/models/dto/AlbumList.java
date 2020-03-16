package com.telerik.ridepalplaylistgenerator.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.telerik.ridepalplaylistgenerator.models.Album;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumList {

    @JsonProperty("data")
    private List<Album> albumList;

    @JsonProperty("next")
    private String nextPageUrl;

    public AlbumList() {
        albumList = new ArrayList<>();
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }
}
