package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.Factory;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.exceptions.TravelDurationException;
import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Playlist;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistGenerationDto;
import com.telerik.ridepalplaylistgenerator.repository.GenreRepository;
import com.telerik.ridepalplaylistgenerator.repository.PlaylistRepository;
import com.telerik.ridepalplaylistgenerator.repository.UserRepository;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceImplTests {

    @Mock
    PlaylistRepository repository;

    @Mock
    GenreRepository genreRepository;

    @Mock
    LocationServiceImpl locationService;

    @Mock
    GeneratePlaylistServiceImpl generatePlaylistService;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    PlaylistServiceImpl playlistService;

    @Test
    public void getAllShould_ReturnPlaylistList(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);

        Mockito.when(repository.findAll())
                .thenReturn(playlists);

        //Act
        List<Playlist> returnedList = playlistService.getAll();

        //Assert
        Assert.assertSame(playlists, returnedList);
    }

    @Test
    public void getByTitleShould_ReturnPlaylist(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);

        Mockito.when(repository.findByTitle(Mockito.anyString()))
                .thenReturn(playlists);

        //Act
        List<Playlist> returnedList = playlistService.getByTitle("Playlist");

        //Assert
        Assert.assertSame(playlists, returnedList);
    }

    @Test
    public void getOrderByRankDescShould_CallRepository(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);

        Mockito.when(repository.findAllByOrderByRankAvgAsc())
                .thenReturn(playlists);

        //Act
        playlistService.getOrderByRankAsc();

        //Assert
        Mockito.verify(repository, Mockito.times(1)).findAllByOrderByRankAvgAsc();
    }

    @Test
    public void getPlaylistByIdShould_ReturnPlaylist(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();

        Mockito.when(repository.getPlaylistById(Mockito.anyInt()))
                .thenReturn(playlist);

        //Act
        Playlist returnedPlaylist = repository.getPlaylistById(1);

        //Assert
        Assert.assertSame(playlist, returnedPlaylist);
    }

    @Test
    public void deleteByIdShould_CallRepository(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();

        Mockito.when(playlistService.getPlaylistById(Mockito.anyInt()))
                .thenReturn(playlist);

        //Act
        playlistService.deleteById(1);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(playlist);
    }

    @Test
    public void updatePlaylistShould_CallRepository_When_PlaylistDoesNotExist(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        Playlist playlistToUpdate = new Playlist();
        List<Playlist> list = new ArrayList<>();
        list.add(playlist);

        Mockito.when(repository.getByTitle(Mockito.anyString()))
                .thenReturn(list);

        Mockito.when(repository.getPlaylistById(Mockito.anyInt()))
                .thenReturn(playlistToUpdate);

        //Act
        playlistService.updatePlaylist(playlist, 1);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(playlistToUpdate);
    }

    @Test
    public void checkTitleExistsShould_CallRepository(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        List<Playlist> list = new ArrayList<>();
        list.add(playlist);

        //Act
        playlistService.checkTitleExists("Pesho");

        //Arrange
        Mockito.verify(repository, Mockito.times(1)).getByTitle("Pesho");
    }

    @Test
    public void getAllPlaylistByUserIdShould_CallRepository(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        List<Playlist> list = new ArrayList<>();
        list.add(playlist);

        Mockito.when(repository.findAllByUserID(Mockito.anyInt()))
                .thenReturn(list);

        //Act
        playlistService.getAllPlaylistByUserId(1);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).findAllByUserID(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void createShould_Throw_When_GenreDoesNotExist() throws JSONException {
        //Arrange
        PlaylistGenerationDto playlistGenerationDto = Factory.createPlaylistGenerationDto();
        Mockito.when(locationService.getTravelDuration(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(300.00);

        //Act, Assert
        playlistService.create(playlistGenerationDto, 1);

    }

    @Test(expected = TravelDurationException.class)
    public void createShould_Throw_When_TravelDurationIsNotFilled() throws JSONException {
        //Arrange
        PlaylistGenerationDto playlistGenerationDto = Factory.createPlaylistGenerationDto();
        Genre genre = Factory.createGenre();
        Track track = Factory.createTrack();
        List<Track> list = new ArrayList<>();
        list.add(track);
        Mockito.when(locationService.getTravelDuration(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(900.00);
        Mockito.when(genreRepository.getGenreById(1))
                .thenReturn(genre);
        Mockito.when(generatePlaylistService.createPlaylistTracks(Mockito.anyInt(), Mockito.any(), Mockito.anyBoolean(),
                 Mockito.anyBoolean()))
                .thenReturn(list);
        Mockito.when(generatePlaylistService.calculatePlaylistDuration(Mockito.anyList()))
                .thenReturn(300);

        //Act, Assert
        playlistService.create(playlistGenerationDto, 1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void createShould_Throw_When_UserIsNotFound() throws JSONException {
        //Arrange
        PlaylistGenerationDto playlistGenerationDto = Factory.createPlaylistGenerationDto();
        Genre genre = Factory.createGenre();
        Track track = Factory.createTrack();
        List<Track> list = new ArrayList<>();
        list.add(track);
        Mockito.when(locationService.getTravelDuration(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(300.00);
        Mockito.when(genreRepository.getGenreById(1))
                .thenReturn(genre);
        Mockito.when(generatePlaylistService.createPlaylistTracks(Mockito.anyInt(), Mockito.any(), Mockito.anyBoolean(),
                Mockito.anyBoolean()))
                .thenReturn(list);
        Mockito.when(generatePlaylistService.calculatePlaylistDuration(Mockito.anyList()))
                .thenReturn(300);
        Mockito.when(userRepository.getUserByUserId(Mockito.anyInt()))
                .thenReturn(null);

        //Act, Assert
        playlistService.create(playlistGenerationDto, 1);
    }

    @Test
    public void createShould_CallPlaylistRepository() throws JSONException {
        //Arrange
        PlaylistGenerationDto playlistGenerationDto = Factory.createPlaylistGenerationDto();
        Genre genre = Factory.createGenre();
        Track track = Factory.createTrack();
        User user = Factory.createUser();
        List<Track> list = new ArrayList<>();
        list.add(track);
        Mockito.when(locationService.getTravelDuration(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(300.00);
        Mockito.when(genreRepository.getGenreById(1))
                .thenReturn(genre);
        Mockito.when(generatePlaylistService.createPlaylistTracks(Mockito.anyInt(), Mockito.any(), Mockito.anyBoolean(),
                Mockito.anyBoolean()))
                .thenReturn(list);
        Mockito.when(generatePlaylistService.calculatePlaylistDuration(Mockito.anyList()))
                .thenReturn(300);
        Mockito.when(userRepository.getUserByUserId(Mockito.anyInt()))
                .thenReturn(user);

        //Act
        Playlist playlist1 = playlistService.create(playlistGenerationDto, 1);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(playlist1);
    }

    @Test
    public void filterByNameShould_CallRepository(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        List<Playlist> list = new ArrayList<>();
        list.add(playlist);
        Mockito.when(repository.filterByName(Mockito.anyString()))
                .thenReturn(list);

        //Act
        playlistService.filterByName("name");

        //Assert
        Mockito.verify(repository, Mockito.times(1)).filterByName("name");
    }

    @Test
    public void filterByDurationShould_CallRepository(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        List<Playlist> list = new ArrayList<>();
        list.add(playlist);
        Mockito.when(repository.filterByDuration(Mockito.anyInt()))
                .thenReturn(list);

        //Act
        playlistService.filterByDuration(100);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).filterByDuration(100);
    }

    @Test
    public void filterByGenreShould_ReturnEmptyList_When_GenreNameDoesNotExist(){
        //Arrange
        List<Playlist> list = new ArrayList<>();
        Mockito.when(repository.findAll())
                .thenReturn(list);

        Mockito.when(genreRepository.getGenreByName(Mockito.anyString()))
                .thenReturn(null);

        //Act
        List<Playlist> returnedList = playlistService.filterByGenre("name");

        //Assert
        Assert.assertEquals(list.size(), returnedList.size());
    }

    @Test
    public void filterByGenreShould_ReturnPopulatedList_When_GenreNameExists(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        Genre genre = Factory.createGenre();
        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(genre);
        playlist.setGenres(genreSet);
        List<Playlist> list = new ArrayList<>();
        list.add(playlist);
        Mockito.when(repository.findAll())
                .thenReturn(list);
        Mockito.when(genreRepository.getGenreByName(Mockito.anyString()))
                .thenReturn(genre);

        //Act
        List<Playlist> returnedList = playlistService.filterByGenre("name");

        //Assert
        Assert.assertEquals(list.get(0), returnedList.get(0));
    }
}
