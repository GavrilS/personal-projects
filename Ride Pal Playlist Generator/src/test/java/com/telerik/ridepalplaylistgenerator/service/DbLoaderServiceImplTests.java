package com.telerik.ridepalplaylistgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telerik.ridepalplaylistgenerator.Factory;
import com.telerik.ridepalplaylistgenerator.models.Album;
import com.telerik.ridepalplaylistgenerator.models.Artist;
import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.models.dto.AlbumList;
import com.telerik.ridepalplaylistgenerator.models.dto.ArtistList;
import com.telerik.ridepalplaylistgenerator.models.dto.GenreList;
import com.telerik.ridepalplaylistgenerator.repository.AlbumRepository;
import com.telerik.ridepalplaylistgenerator.repository.ArtistRepository;
import com.telerik.ridepalplaylistgenerator.repository.GenreRepository;
import com.telerik.ridepalplaylistgenerator.repository.TrackRepository;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DbLoaderServiceImplTests {

    @Mock
    RestTemplate restTemplate;

    @Mock
    ArtistRepository artistRepository;

    @Mock
    AlbumRepository albumRepository;

    @Mock
    TrackRepository trackRepository;

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    DbLoaderServiceImpl dbLoaderService;

    @Test
    public void loadArtistsbyGenreShould_CallArtistRepository(){
        //Arrange
        Genre genre = Factory.createGenre();
        Artist artist = Factory.createArtist();
        Artist artist2 = new Artist();
        List<Artist> list = new ArrayList<>();
        list.add(artist);
        list.add(artist2);
        ArtistList artistList = new ArtistList();
        artistList.setListOfArtists(list);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any()))
                .thenReturn(artistList);

        //Act
        dbLoaderService.loadArtistsByGenre(genre.getId());

        //Assert
        Mockito.verify(artistRepository, Mockito.times(1)).save(artist);
    }

    @Test
    public void loadAlbumsFromArtistsShould_CallAlbumRepository(){
        //Arrange
        String urlToGetArtists = "https://api.deezer.com/genre/" + 1 + "/artists";
        String urlForAlbumsFromArtists = "https://api.deezer.com/artist/" + 1 + "/albums";
        String urlForIndividualAlbums = "https://api.deezer.com/album/" + 1;
        Album album = Factory.createAlbum();
        List<Album> list = new ArrayList<>();
        list.add(album);
        AlbumList albumList = new AlbumList();
        albumList.setAlbumList(list);
        Artist artist = Factory.createArtist();
        List<Artist> artList = new ArrayList<>();
        artList.add(artist);
        ArtistList artistList = new ArtistList();
        artistList.setListOfArtists(artList);

        Mockito.when(restTemplate.getForObject(urlToGetArtists, ArtistList.class))
                .thenReturn(artistList);
        Mockito.when(restTemplate.getForObject(urlForAlbumsFromArtists, AlbumList.class))
                .thenReturn(albumList);
        Mockito.when(restTemplate.getForObject(urlForIndividualAlbums, Album.class))
                .thenReturn(album);

        //Act
        dbLoaderService.loadAlbumsFromArtists(1);

        //Assert
        Mockito.verify(albumRepository, Mockito.times(1)).saveAll(list);
    }

    @Test
    public void populateSongsByGenreShould_CallTrackRepository() throws ParseException, JsonProcessingException {
        //Arrange
        String urlToGetArtists = "https://api.deezer.com/genre/" + 1 + "/artists";
        String urlForAlbumsFromArtists = "https://api.deezer.com/artist/" + 1 + "/albums";
        String urlForIndividualAlbums = "https://api.deezer.com/album/" + 1;
        String data = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": \"3135553\",\n" +
                "}\n" +
                "  ],\n" +
                "  \"total\": 14\n" +
                "}";
        Album album = Factory.createAlbum();
        List<Album> list = new ArrayList<>();
        list.add(album);
        AlbumList albumList = new AlbumList();
        albumList.setAlbumList(list);
//        Track track = new Track();
//        track.setId(3135553);
//        track.setAlbum(album);
//        track.setGenreId(1);
        Artist artist = Factory.createArtist();
        List<Artist> artList = new ArrayList<>();
        artList.add(artist);
        ArtistList artistList = new ArtistList();
        artistList.setListOfArtists(artList);
        Genre genre = Factory.createGenre();

        Mockito.when(genreRepository.getGenreById(1))
                .thenReturn(genre);
        Mockito.when(restTemplate.getForObject(urlToGetArtists, ArtistList.class))
                .thenReturn(artistList);
        Mockito.when(restTemplate.getForObject(urlForAlbumsFromArtists, AlbumList.class))
                .thenReturn(albumList);
        Mockito.when(restTemplate.getForObject(urlForIndividualAlbums, Album.class))
                .thenReturn(album);
        Mockito.when(restTemplate.getForObject(album.getTracklist(), String.class))
                .thenReturn(data);
        Mockito.when(albumRepository.getOne(album.getId()))
                .thenReturn(album);

        //Act
        dbLoaderService.populateSongsByGenre(1);

        //Assert
        Mockito.verify(albumRepository, Mockito.times(1)).getOne(album.getId());
    }

    @Test
    public void loadInitialGenresShould_CallRepository(){
        //Arrange
        String url = "https://api.deezer.com/genre";
        Genre genre = Factory.createGenre();
        List<Genre> list = new ArrayList<>();
        list.add(genre);
        GenreList genreList = new GenreList();
        genreList.setListOfGenres(list);
        Mockito.when(restTemplate.getForObject(url, GenreList.class))
                .thenReturn(genreList);

        //Act
        dbLoaderService.loadInitialGenres();

        //Assert
        Mockito.verify(genreRepository, Mockito.times(1)).save(genre);
    }

    @Test
    public void databaseInitializerShould_PopulateSongs() throws ParseException, JsonProcessingException {
        //Arrange
        String urlToGetArtists116 = "https://api.deezer.com/genre/" + 116 + "/artists";
        String urlToGetArtists129 = "https://api.deezer.com/genre/" + 129 + "/artists";
        String urlToGetArtists152 = "https://api.deezer.com/genre/" + 152 + "/artists";
        String urlForAlbumsFromArtists = "https://api.deezer.com/artist/" + 1 + "/albums";
        String urlForIndividualAlbums = "https://api.deezer.com/album/" + 1;
        String data = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": \"3135553\",\n" +
                "}\n" +
                "  ],\n" +
                "  \"total\": 14\n" +
                "}";
        Album album = Factory.createAlbum();
        List<Album> list = new ArrayList<>();
        list.add(album);
        AlbumList albumList = new AlbumList();
        albumList.setAlbumList(list);
//        Track track = new Track();
//        track.setId(3135553);
//        track.setAlbum(album);
//        track.setGenreId(1);
        Artist artist = Factory.createArtist();
        List<Artist> artList = new ArrayList<>();
        artList.add(artist);
        ArtistList artistList = new ArtistList();
        artistList.setListOfArtists(artList);
        Genre genre = Factory.createGenre();
        GenreList genreList = new GenreList();
        List<Genre> listGenre = new ArrayList<>();
        listGenre.add(genre);
        genreList.setListOfGenres(listGenre);

        Mockito.when(restTemplate.getForObject("https://api.deezer.com/genre", GenreList.class))
                .thenReturn(genreList);
        Mockito.when(genreRepository.getGenreById(116))
                .thenReturn(genre);
        Mockito.when(genreRepository.getGenreById(129))
                .thenReturn(genre);
        Mockito.when(genreRepository.getGenreById(152))
                .thenReturn(genre);
        Mockito.when(restTemplate.getForObject(urlToGetArtists116, ArtistList.class))
                .thenReturn(artistList);
        Mockito.when(restTemplate.getForObject(urlToGetArtists129, ArtistList.class))
                .thenReturn(artistList);
        Mockito.when(restTemplate.getForObject(urlToGetArtists152, ArtistList.class))
                .thenReturn(artistList);
        Mockito.when(restTemplate.getForObject(urlForAlbumsFromArtists, AlbumList.class))
                .thenReturn(albumList);
        Mockito.when(restTemplate.getForObject(urlForIndividualAlbums, Album.class))
                .thenReturn(album);
//        Mockito.when(restTemplate.getForObject(album.getTracklist(), String.class))
//                .thenReturn(data);
//        Mockito.when(albumRepository.getOne(album.getId()))
//                .thenReturn(album);

        //Act
        dbLoaderService.databaseInitializer();

        //Assert
        Mockito.verify(genreRepository, Mockito.times(3)).saveAndFlush(genre);
    }
}