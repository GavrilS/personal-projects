package com.telerik.ridepalplaylistgenerator.service.interfaces;

import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Track;

import java.util.HashMap;
import java.util.List;

public interface GeneratePlaylistService {

    List<Track> createPlaylistTracks(int duration, HashMap<Genre, Integer> durationPercentByGenre, boolean useSameArtist,
                                     boolean useTopTracks);

    int calculatePlaylistDuration(List<Track> playlist);

    int calculatePlaylistRank(List<Track> playlist);
}
