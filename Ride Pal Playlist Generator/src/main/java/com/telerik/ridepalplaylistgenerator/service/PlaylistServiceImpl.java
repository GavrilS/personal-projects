package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.exceptions.DuplicateEntityException;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.exceptions.GenreDurationException;
import com.telerik.ridepalplaylistgenerator.exceptions.TravelDurationException;
import com.telerik.ridepalplaylistgenerator.models.*;
import com.telerik.ridepalplaylistgenerator.models.dto.GenrePercentageDto;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistGenerationDto;
import com.telerik.ridepalplaylistgenerator.repository.*;
import com.telerik.ridepalplaylistgenerator.service.interfaces.GeneratePlaylistService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.LocationService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.PlaylistService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private PlaylistRepository playlistRepository;
    private GeneratePlaylistService generatePlaylistService;
    private LocationService locationService;
    private UserRepository userRepository;
    private GenreRepository genreRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository, GeneratePlaylistService generatePlaylistService,
                               LocationService locationService, UserRepository userRepository, GenreRepository genreRepository) {
        this.playlistRepository = playlistRepository;
        this.generatePlaylistService = generatePlaylistService;
        this.locationService = locationService;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Playlist> getAll() {
        return playlistRepository.findAll();
    }

    @Override
    public List<Playlist> getByTitle(String title) {
        return playlistRepository.findByTitle(title);
    }

    @Override
    public List<Playlist> getOrderByRankAsc() {
        return playlistRepository.findAllByOrderByRankAvgAsc();
    }

    @Override
    public Playlist getPlaylistById(int id) {
        return playlistRepository.getPlaylistById(id);
    }

    @Override
    public void deleteById(int id) {
        Playlist playlistToUpdate = getPlaylistById(id);
        if(playlistToUpdate == null){
            throw new EntityNotFoundException(String.format("Playlist with id %d does not exist.", id));
        }
        playlistToUpdate.setEnabled(false);
        playlistRepository.save(playlistToUpdate);
    }

    @Override
    public Playlist updatePlaylist(Playlist playlist, int id) {
        if (checkTitleExists(playlist.getTitle())) {
            if (!(playlist.getUserId() == playlistRepository.getByTitle(playlist.getTitle()).get(0).getUserId())) {
                throw new DuplicateEntityException(String.format(TITLE_EXISTS_MESSAGE, playlist.getTitle()));
            }
        }
        Playlist playlistToUpdate = getPlaylistById(id);
        if(playlistToUpdate == null){
            throw new EntityNotFoundException(String.format("Playlist with id %d does not exist.", id));
        }
        if (playlist.getTitle() != null) {
            playlistToUpdate.setTitle(playlist.getTitle());
        }
        if (playlist.getDescription() != null) {
            playlistToUpdate.setDescription(playlist.getDescription());
        }
        if (playlist.getPicture() != null) {
            playlistToUpdate.setPicture(playlist.getPicture());
        }
        return playlistRepository.save(playlistToUpdate);
    }

    public boolean checkTitleExists(String title) {
        return playlistRepository.getByTitle(title).size() != 0;
    }


    @Override
    public List<Playlist> filterByName(String name) {
        return playlistRepository.filterByName(name);
    }

    @Override
    public List<Playlist> filterByGenre(String name) {
        List<Playlist> fullList = playlistRepository.findAll();
        List<Playlist> filteredList = new ArrayList<>();
        Genre genre = genreRepository.getGenreByName(name);
        if(genre == null){
            return filteredList;
        }
        for(Playlist playlist : fullList){
            if(playlist.getGenres().contains(genre)){
                filteredList.add(playlist);
            }
        }
        return filteredList;
    }

    @Override
    public List<Playlist> filterByDuration(int duration) {
        return playlistRepository.filterByDuration(duration);
    }

    @Override
    public List<Playlist> getAllPlaylistByUserId(int userId) {
        return playlistRepository.findAllByUserID(userId);
    }

    @Override
    public Playlist create(PlaylistGenerationDto playlistGeneration, int userId) throws JSONException {
        Playlist playlist = new Playlist();
        playlist.setTitle(playlistGeneration.getPlaylistTitle());
        playlist.setDescription(playlistGeneration.getPlaylistDescription());
        int travelDuration = (int) locationService.getTravelDuration(playlistGeneration.getStartLocation(),
                        playlistGeneration.getEndLocation());
        HashMap<Integer, Integer> map = new HashMap<>();
        try{
            map = createMap(playlistGeneration);
        } catch (GenreDurationException e){
            e.getMessage();
        }
        HashMap<Genre, Integer> completeMap = new HashMap<>();
        Set<Genre> genres = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Genre genre = genreRepository.getGenreById(entry.getKey());
            if (genre == null) {
                throw new EntityNotFoundException(String.format("Genre with id %d does not exist.", entry.getKey()));
            }
            genres.add(genre);
            completeMap.put(genre, entry.getValue());
        }
        playlist.setGenres(genres);

        List<Track> trackList = generatePlaylistService.createPlaylistTracks(travelDuration, completeMap,
                playlistGeneration.isUseSameArtist(), playlistGeneration.isUseTopTracks());
        playlist.setTracks(trackList);
        int playListDuration = generatePlaylistService.calculatePlaylistDuration(trackList);
        if (playListDuration < travelDuration - 300) {
            throw new TravelDurationException();
        }
        playlist.setDuration(playListDuration);

        User user = userRepository.getUserByUserId(userId);
        if (user == null) {
            throw new EntityNotFoundException(String.format("User with id %d does not exist.", userId));
        }
        playlist.setUserId(user);

        playlist.setRank(generatePlaylistService.calculatePlaylistRank(trackList));

        playlistRepository.save(playlist);
        return playlist;
    }

    private HashMap<Integer, Integer> createMap(PlaylistGenerationDto playlistGeneration){
        List<GenrePercentageDto> genrePercentageDtoList = new ArrayList<>();
        genrePercentageDtoList.add(playlistGeneration.getGenre1());
        genrePercentageDtoList.add(playlistGeneration.getGenre2());
        genrePercentageDtoList.add(playlistGeneration.getGenre3());
        HashMap<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        for (GenrePercentageDto genre : genrePercentageDtoList) {
            if (genre.getGenreId() > 0 && genre.getGenrePercent() > 0) {
                map.put(genre.getGenreId(), genre.getGenrePercent());
                counter += genre.getGenrePercent();
            }
        }
        if(counter != 100){
            throw new GenreDurationException();
        }
        return map;
    }

}