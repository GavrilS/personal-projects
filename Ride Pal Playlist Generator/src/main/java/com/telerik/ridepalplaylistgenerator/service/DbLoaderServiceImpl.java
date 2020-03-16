package com.telerik.ridepalplaylistgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
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
import com.telerik.ridepalplaylistgenerator.service.interfaces.DbLoaderService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DbLoaderServiceImpl implements DbLoaderService {
    private GenreRepository genreRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private ArtistRepository artistRepository;
    private RestTemplate restTemplate;

    @Autowired
    public DbLoaderServiceImpl(GenreRepository genreRepository, AlbumRepository albumRepository, TrackRepository trackRepository,
                               ArtistRepository artistRepository, RestTemplate restTemplate) {
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void populateSongsByGenre(long id) throws ParseException, JsonProcessingException {
        Genre genre = genreRepository.getGenreById(id);
        if(genre == null){
            throw new EntityNotFoundException(String.format("No genre with id %d was found.", id));
        }
        List<Album> albums = loadAlbumsFromArtists(id);
        JSONParser jsonParser = new JSONParser();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Album album : albums) {

            String url = album.getTracklist();
            System.out.println(url);
            if (url.isEmpty()) {
                continue;
            }
            String response1 = restTemplate.getForObject(
                    url,
                    String.class);
            JSONObject jsonObject1 = (JSONObject) jsonParser.parse(response1);
            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("data");
            String track = objectMapper.writeValueAsString(jsonArray1);
            List<Track> result = objectMapper.readValue(track, new TypeReference<List<Track>>() {
            });
            int trackCount = 0;
            List<Track> tracks = new ArrayList<>();
            for (Track track1 : result) {
                if (trackCount > 10) {
                    break;
                }
                track1.setAlbum(albumRepository.getOne(album.getId()));
                track1.setGenreId(track1.getAlbum().getGenreId());
                tracks.add(track1);
                trackCount++;
            }
            // Save the list into a database
            if (Objects.nonNull(tracks))
                result.stream().filter(Objects::nonNull).forEach(element -> trackRepository.saveAndFlush(element));
        }
        genre.setEnabled(true);
        genreRepository.saveAndFlush(genre);
    }

    @Override
    public List<Album> loadAlbumsFromArtists(long genreId) {
        List<Album> albums = new ArrayList<>();
        List<Artist> artists = loadArtistsByGenre(genreId);
        String urlForAlbumsFromArtists = "https://api.deezer.com/artist/";
        String urlForIndividualAlbums = "https://api.deezer.com/album/";
        int counter = 0;
        for (Artist artist : artists) {
            AlbumList listOfAlbums = restTemplate.getForObject(urlForAlbumsFromArtists + artist.getId() + "/albums",
                    AlbumList.class);
            for (Album albumListItem : listOfAlbums.getAlbumList()) {
                Album album = restTemplate.getForObject(urlForIndividualAlbums + albumListItem.getId(), Album.class);
                if (album.getGenreId() == genreId) {
                    albums.add(album);
                    counter++;
                }
                if (counter == 5) {
                    break;
                }
            }
            counter = 0;
        }
        albumRepository.saveAll(albums);
        return albums;
    }

    @Override
    public List<Artist> loadArtistsByGenre(long genreId) {
        String urlToGetArtists = "https://api.deezer.com/genre/" + genreId + "/artists";
        ArtistList artistsByGenre = restTemplate.getForObject(urlToGetArtists, ArtistList.class);
        List<Artist> artists = new ArrayList<>();
        for (Artist artist : artistsByGenre.getListOfArtists()) {
            artists.add(artist);
            artistRepository.save(artist);
        }
        return artists;
    }

    @Override
    public void loadInitialGenres() {
        String url = "https://api.deezer.com/genre";
        GenreList newGenre = restTemplate.getForObject(url, GenreList.class);
        List<Genre> genres = new ArrayList<>();
        for (Genre genre : newGenre.getListOfGenres()) {
            genres.add(genre);
            genreRepository.save(genre);
        }
    }

    @Override
    public void databaseInitializer() throws ParseException, JsonProcessingException {
        loadInitialGenres();
        List<Long> starterGenresById = new ArrayList<>();
        starterGenresById.add(116L);
        starterGenresById.add(152L);
        starterGenresById.add(129L);
        //Info about genres:
//        rapGenreId = 116;
//        rockGenreId = 152;
//        jazzGenreId = 129;
        for(Long genreId : starterGenresById){
            populateSongsByGenre(genreId);
        }
    }
}