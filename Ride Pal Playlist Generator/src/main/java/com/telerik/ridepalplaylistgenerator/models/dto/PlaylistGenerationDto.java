package com.telerik.ridepalplaylistgenerator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PlaylistGenerationDto {
    private String startLocation;
    private String endLocation;
    private GenrePercentageDto genre1;
    private GenrePercentageDto genre2;
    private GenrePercentageDto genre3;
    private String playlistTitle;
    private String playlistDescription;
    private boolean useTopTracks;
    private boolean useSameArtist;

}
