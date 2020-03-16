package com.telerik.ridepalplaylistgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @Column(name = "album_id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cover")
    private String cover;

    @Column(name = "duration")
    private int duration;

    @JsonProperty("genre_id")
    @Column(name = "genre_id")
    private long genreId;

    @Column(name = "tracklist")
    private String tracklist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    private Artist artist;

}