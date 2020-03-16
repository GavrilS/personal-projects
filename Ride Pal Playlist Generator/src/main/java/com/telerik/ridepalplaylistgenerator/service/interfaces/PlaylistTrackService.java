package com.telerik.ridepalplaylistgenerator.service.interfaces;

import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.models.dto.TrackDto;

import java.util.List;

public interface PlaylistTrackService {
    List<TrackDto> toDTO(List<Track> tracks);

    List<Track> getTracksByPlaylistId(int playlistId);
}
