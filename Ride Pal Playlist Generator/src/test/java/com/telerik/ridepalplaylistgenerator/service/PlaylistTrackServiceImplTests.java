package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.models.PlaylistTrack;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.models.dto.TrackDto;
import com.telerik.ridepalplaylistgenerator.repository.PlaylistTrackRepository;
import com.telerik.ridepalplaylistgenerator.repository.TrackRepository;
import com.telerik.ridepalplaylistgenerator.service.PlaylistTrackServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistTrackServiceImplTests {

    @Mock
    PlaylistTrackRepository playlistTrackRepository;

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    PlaylistTrackServiceImpl playlistTrackService;

    @Test
    public void toDtoShould_ReturnListOfTrackDto(){
        //Arrange
        TrackDto trackDto = new TrackDto();
        List<TrackDto> list = new ArrayList<>();
        list.add(trackDto);
        Track track = new Track();
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);

        //Act
        List<TrackDto> dto = playlistTrackService.toDTO(tracks);

        //Assert
        Assert.assertEquals(list.get(0).getId(), dto.get(0).getId());
    }

    @Test
    public void getTracksByPlaylistIdShould_ReturnListOfTracks_When_PassedValidPlaylistId(){
        //Arrange
        PlaylistTrack trackFromPlaylist = new PlaylistTrack();
        trackFromPlaylist.setId(1);
        List<PlaylistTrack> list = new ArrayList<>();
        list.add(trackFromPlaylist);

        Mockito.when(playlistTrackRepository.findAllByPlaylistId(1))
                .thenReturn(list);

//        Mockito.when(trackRepository.findById((long) trackFromPlaylist.getId()).orElse(null))
//                .thenReturn(null);

        //Act
        List<Track> receivedTracks = playlistTrackService.getTracksByPlaylistId(1);

        //Assert
        Assert.assertEquals(null, receivedTracks.get(0));
    }
}
