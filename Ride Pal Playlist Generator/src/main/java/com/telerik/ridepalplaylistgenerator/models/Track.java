package com.telerik.ridepalplaylistgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @Column(name = "track_id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private int duration;

    @Column(name = "rank")
    private int rank;

    @Column(name = "preview")
    private String preview;

    @Column(name = "genre_id")
    private long genreId;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

   //TODO Genre connection
}