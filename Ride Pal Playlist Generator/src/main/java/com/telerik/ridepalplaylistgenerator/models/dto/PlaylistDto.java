package com.telerik.ridepalplaylistgenerator.models.dto;

import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Playlist;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PlaylistDto {
    @NotNull
    @NotBlank
    @Size(min = TITLE_NAME_MIN_LENGTH,
            max = TITLE_NAME_MAX_LENGTH,
            message = TITLE_INVALID_NAME_LENGTH_ERROR_MSG)
    private String title;

    private int id;

    private String picture;

    @NotBlank
    @Size(max = DESCRIPTION_MAX_LENGTH,
            message = DESCRIPTION_INVALID_LENGTH_ERROR_MSG)
    private String description;

    private int duration;

    private int rank;

    private User userId;

    private List<Track> tracks=new ArrayList<>();

    private Set<Genre> genreSet=new HashSet<>();

    public static PlaylistDto fromModel(Playlist playlist) {
        PlaylistDto playlistDTO = new PlaylistDto();
        playlistDTO.id = playlist.getPlaylistId();
        playlistDTO.title = playlist.getTitle();
        playlistDTO.picture = playlist.getPicture();
        if (playlist.getDuration() != 0) {
            playlistDTO.duration = playlist.getDuration();
        } else {
            playlistDTO.duration = playlist.getDuration();
        }
        playlistDTO.rank = playlist.getRank();
        playlistDTO.tracks=playlist.getTracks();
        return playlistDTO;
    }
}
