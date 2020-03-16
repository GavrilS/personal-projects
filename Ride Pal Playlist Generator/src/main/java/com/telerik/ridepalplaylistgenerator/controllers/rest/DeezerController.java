package com.telerik.ridepalplaylistgenerator.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telerik.ridepalplaylistgenerator.models.Album;
import com.telerik.ridepalplaylistgenerator.models.Artist;
import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.repository.AlbumRepository;
import com.telerik.ridepalplaylistgenerator.repository.ArtistRepository;
import com.telerik.ridepalplaylistgenerator.repository.GenreRepository;
import com.telerik.ridepalplaylistgenerator.service.DbLoaderServiceImpl;
import com.telerik.ridepalplaylistgenerator.repository.TrackRepository;
import com.telerik.ridepalplaylistgenerator.service.interfaces.DbLoaderService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeezerController {
    private GenreRepository genreRepository;
    private TrackRepository trackRepository;
    private DbLoaderService dbl;

    @Autowired
    public DeezerController(DbLoaderService dbl, GenreRepository genreRepository, TrackRepository trackRepository) {
        this.dbl = dbl;
        this.genreRepository = genreRepository;
        this.trackRepository = trackRepository;
    }

    //Fill database with all Deezer genres + tracks from 3 predetermined genres(rap, rock & jazz).
    //It takes around 10 minutes to complete the download of genres, tracks, artists and albums. Slow process
    @GetMapping("/initializer")
    public void initializeDb() throws ParseException, JsonProcessingException {
        dbl.databaseInitializer();
    }

    //Download all Deezer genres to database
    @GetMapping("/starter")
    public List<Genre> startDbGenres()  {
        dbl.loadInitialGenres();
        return genreRepository.findAll();
    }

    //Download artists by specific genre id
    @GetMapping("/artist/{id}")
    public List<Artist> addArtistsByGenre(@PathVariable long id){
        return dbl.loadArtistsByGenre(id);
    }

    //Download albums by specific genre id
    @GetMapping("/album/{id}")
    public List<Album> addAlbumsByArtistGenre(@PathVariable long id){
        return dbl.loadAlbumsFromArtists(id);
    }

    //Download tracks + albums + artists by specific genre id
    @GetMapping("/track/{id}")
    public void getTrackFromAlbumsByGenre(@PathVariable long id) throws ParseException, JsonProcessingException {
        dbl.populateSongsByGenre(id);
    }

    @GetMapping("/genres")
    public List<Genre> getGenres(){
        return genreRepository.findAll();
    }

    @GetMapping("/tracks/all")
    public List<Track> getAllTracks(){
        return trackRepository.findAll();
    }
}