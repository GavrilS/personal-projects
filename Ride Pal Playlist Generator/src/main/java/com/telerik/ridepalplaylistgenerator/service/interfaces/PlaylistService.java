package com.telerik.ridepalplaylistgenerator.service.interfaces;

import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Playlist;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistGenerationDto;
import com.telerik.ridepalplaylistgenerator.models.dto.TrackDto;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

public interface PlaylistService {
    List<Playlist> getAll();

    List<Playlist> getByTitle(String title);

    List<Playlist> getOrderByRankAsc();

    Playlist getPlaylistById(int id);

    void deleteById(int id);

    Playlist updatePlaylist(Playlist playlist, int id);

    void deleteByUserId(Integer id);

    List<Playlist> filterByName(String name);

    List<Playlist> filterByGenre(String name);

    List<Playlist> filterByDuration(int duration);

    List<Playlist> getAllPlaylistByUserId(int userId);

    Playlist create(PlaylistGenerationDto playlistGenerationDto, int user) throws JSONException;
}
