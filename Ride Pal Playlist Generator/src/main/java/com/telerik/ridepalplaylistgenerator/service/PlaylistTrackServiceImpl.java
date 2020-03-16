package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.models.PlaylistTrack;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.models.dto.TrackDto;
import com.telerik.ridepalplaylistgenerator.repository.PlaylistTrackRepository;
import com.telerik.ridepalplaylistgenerator.repository.TrackRepository;
import com.telerik.ridepalplaylistgenerator.service.interfaces.PlaylistService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.PlaylistTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.telerik.ridepalplaylistgenerator.models.dto.TrackDto.fromModel;

@Service
public class PlaylistTrackServiceImpl implements PlaylistTrackService {

    private PlaylistTrackRepository playlistTrackRepository;
    private TrackRepository trackRepository;

    @Autowired
    public PlaylistTrackServiceImpl(PlaylistTrackRepository playlistTrackRepository, TrackRepository trackRepository) {
        this.playlistTrackRepository = playlistTrackRepository;
        this.trackRepository = trackRepository;
    }

    @Override
    public List<TrackDto> toDTO(List<Track> tracks) {
        List<TrackDto> tracksDTO = new ArrayList<>();
        for (Track track : tracks) {
            tracksDTO.add(fromModel(track));
        }
        return tracksDTO;
    }

    @Override
    public List<Track> getTracksByPlaylistId(int playlistId) {
        List<PlaylistTrack> playlistTracks = playlistTrackRepository.findAllByPlaylistId(playlistId);
        List<Track> trackId = new ArrayList<>();
        for (PlaylistTrack playlistTrack : playlistTracks) {
            trackId.add(trackRepository.findById(playlistTrack.getTrackId()).orElse(null));
        }
        return trackId;
    }
}
