package com.telerik.ridepalplaylistgenerator.service.interfaces;

import com.telerik.ridepalplaylistgenerator.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getEnabledGenres();

    List<Genre> getAllGenres();
}
