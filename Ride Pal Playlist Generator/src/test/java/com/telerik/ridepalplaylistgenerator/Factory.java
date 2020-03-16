package com.telerik.ridepalplaylistgenerator;

import com.telerik.ridepalplaylistgenerator.models.*;
import com.telerik.ridepalplaylistgenerator.models.dto.GenrePercentageDto;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistGenerationDto;

public class Factory {

    public static User createUser(){
        User user = new User();
        user.setUserId(1);
        user.setUsername("Pesho");
        user.setEmail("User email");
        user.setPassword("UserPassword");
        user.setPasswordConfirmation("UserPassword");
        user.setFirstName("Pesho");
        user.setLastName("Peshov");
        user.setPicture("Picture");
        return user;
    }

    public static Playlist createPlaylist(){
        Playlist playlist = new Playlist();
        playlist.setUserId(createUser());
        playlist.setTitle("Playlist");
        playlist.setPlaylistId(1);
        playlist.setRank(2);
        playlist.setDescription("Just a random playlist.");
        return playlist;
    }

    public static Track createTrack(){
        Track track = new Track();
        track.setId(1);
        track.setGenreId(1);
        track.setDuration(300);
        track.setTitle("Gangsta paradise");
        track.setRank(3);
        return track;
    }

    public static Genre createGenre(){
        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("Rap");
        return genre;
    }

    public static Artist createArtist(){
        Artist artist = new Artist();
        artist.setId(1);
        artist.setName("Pesho");
        return artist;
    }

    public static Album createAlbum(){
        Album album = new Album();
        album.setId(1);
        album.setGenreId(1);
        album.setTracklist("TrackList");
        return album;
    }

    public static PlaylistGenerationDto createPlaylistGenerationDto(){
        PlaylistGenerationDto playlistGenDto = new PlaylistGenerationDto();
        playlistGenDto.setStartLocation("Sofia");
        playlistGenDto.setEndLocation("Plovdiv");
        playlistGenDto.setGenre1(createGenrePercentageDto());
        playlistGenDto.setGenre2(createEmptyGenrePercDto());
        playlistGenDto.setGenre3(createEmptyGenrePercDto());
        playlistGenDto.setPlaylistTitle("Tester");
        playlistGenDto.setPlaylistDescription("Test-tester");
        playlistGenDto.setUseSameArtist(true);
        playlistGenDto.setUseTopTracks(true);
        return playlistGenDto;
    }

    public static GenrePercentageDto createGenrePercentageDto(){
        GenrePercentageDto genrePercDto = new GenrePercentageDto();
        genrePercDto.setGenreId(1);
        genrePercDto.setGenrePercent(100);
        return genrePercDto;
    }

    public static GenrePercentageDto createEmptyGenrePercDto(){
        GenrePercentageDto empty = new GenrePercentageDto();
        empty.setGenreId(0);
        empty.setGenrePercent(0);
        return empty;
    }
}
