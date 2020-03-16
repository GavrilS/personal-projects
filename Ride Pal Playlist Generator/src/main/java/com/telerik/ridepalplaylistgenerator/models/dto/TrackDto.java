package com.telerik.ridepalplaylistgenerator.models.dto;

import com.telerik.ridepalplaylistgenerator.models.Track;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TrackDto {
    public long id;

    public String title;

    public Integer rank;

    public String preview;

    public  String artist;

    public String album;

    public long genre;

    public int duration;

    public static TrackDto fromModel(Track track) {
        TrackDto trackDTO = new TrackDto();
        trackDTO.id = track.getId();
        trackDTO.title = track.getTitle();
        trackDTO.rank = track.getRank();
        trackDTO.preview = track.getPreview();
        if(track.getArtist() != null){
            trackDTO.artist = track.getArtist().getName();
        }
        if(track.getAlbum() != null){
            trackDTO.album = track.getAlbum().getTitle();
        }
        trackDTO.genre = track.getGenreId();
        trackDTO.duration = track.getDuration();
        return trackDTO;
    }
}
