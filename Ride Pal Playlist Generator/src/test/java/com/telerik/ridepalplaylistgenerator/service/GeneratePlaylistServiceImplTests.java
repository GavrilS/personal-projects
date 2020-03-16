package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.Factory;
import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Playlist;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GeneratePlaylistServiceImplTests {

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    GeneratePlaylistServiceImpl generatePlaylistService;

    @Test
    public void calculatePlaylistDurationShould_ReturnCorrectDuration(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        Track track = Factory.createTrack();
        List<Track> list = new ArrayList<>();
        list.add(track);
        playlist.setTracks(list);

        //Act
        int duration = generatePlaylistService.calculatePlaylistDuration(list);

        //Assert
        Assert.assertEquals(track.getDuration(), duration);
    }

    @Test
    public void  createPlaylistTracksShould_CreateAListOfAllTracksByGenre(){
        //Arrange
        Track track = Factory.createTrack();
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        Genre genre = Factory.createGenre();
        int timeForGenre = 100;
        HashMap<Genre, Integer> map = new HashMap<>();
        map.put(genre, timeForGenre);
        Mockito.when(trackRepository.getTracksByGenre(genre.getId(), 300))
                .thenReturn(tracks);

        //Act
        List<Track> receivedTracks = generatePlaylistService.createPlaylistTracks(300, map, true,
                false);

        //Assert
        Assert.assertEquals(track, receivedTracks.get(0));
    }

    @Test
    public void  createPlaylistTracksShould_CreateAListOfTopTracksByGenre(){
        //Arrange
        Track track = Factory.createTrack();
        List<Track> tracks = new ArrayList<>();
        List<Track> expectedResult = new ArrayList<>();
        tracks.add(track);
        expectedResult.add(track);
        Genre genre = Factory.createGenre();
        int timeForGenre = 100;
        HashMap<Genre, Integer> map = new HashMap<>();
        map.put(genre, timeForGenre);
        Mockito.when(trackRepository.getTracksByGenreAndRank(genre.getId(), 300))
                .thenReturn(tracks);

        //Act
        List<Track> receivedTracks = generatePlaylistService.createPlaylistTracks(300, map, true,
                true);

        //Assert
        Assert.assertEquals(expectedResult, receivedTracks);
    }

    @Test
    public void createPlaylistTracksShould_CallRepository_When_TopTracksIsSelected(){
        //Arrange
        Track track = Factory.createTrack();
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        Genre genre = Factory.createGenre();
        int timeForGenre = 100;
        HashMap<Genre, Integer> map = new HashMap<>();
        map.put(genre, timeForGenre);
        Mockito.when(trackRepository.getTracksByGenreAndRank(genre.getId(), 300))
                .thenReturn(tracks);

        //Act
        List<Track> receivedTracks = generatePlaylistService.createPlaylistTracks(300, map, true,
                true);

        //Assert
        Mockito.verify(trackRepository, Mockito.times(1)).getTracksByGenreAndRank(genre.getId(),
                 300);
    }

    @Test
    public void createPlaylistTracksShould_CallRepository_When_TopTracksIsNotSelected(){
        //Arrange
        Track track = Factory.createTrack();
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        Genre genre = Factory.createGenre();
        int timeForGenre = 100;
        HashMap<Genre, Integer> map = new HashMap<>();
        map.put(genre, timeForGenre);
        Mockito.when(trackRepository.getTracksByGenre(genre.getId(), 300))
                .thenReturn(tracks);

        //Act
        List<Track> receivedTracks = generatePlaylistService.createPlaylistTracks(300, map, true,
                false);

        //Assert
        Mockito.verify(trackRepository, Mockito.times(1)).getTracksByGenre(genre.getId(),
                300);
    }

    @Test
    public void calculatePlaylistRankShould_CalculateRank(){
        //Arrange
        Playlist playlist = Factory.createPlaylist();
        Track track = Factory.createTrack();
        track.setRank(10);
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        playlist.setTracks(tracks);
        int rank = 10;

        //Act
        int returnedRank = generatePlaylistService.calculatePlaylistRank(playlist.getTracks());

        //Assert
        Assert.assertEquals(rank, returnedRank);
    }
}