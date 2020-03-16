package com.telerik.ridepalplaylistgenerator.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telerik.ridepalplaylistgenerator.models.Album;
import com.telerik.ridepalplaylistgenerator.models.Artist;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface DbLoaderService {

    void populateSongsByGenre(long id) throws ParseException, JsonProcessingException;

    List<Album> loadAlbumsFromArtists(long id);

    List<Artist> loadArtistsByGenre(long id);

    void loadInitialGenres();

    void databaseInitializer() throws ParseException, JsonProcessingException;
}
