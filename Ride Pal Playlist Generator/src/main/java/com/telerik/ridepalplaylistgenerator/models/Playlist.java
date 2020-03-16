package com.telerik.ridepalplaylistgenerator.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.telerik.ridepalplaylistgenerator.constants.ValidationHelper.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "playlists")

public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private int playlistId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "rank")
    private int rank;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "cover")
    private String picture;

    @Column(name = "enabled")
    private boolean enabled=true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tracks_playlists",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id")
    )
    private List<Track> tracks=new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "playlists_genres",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns =@JoinColumn(name="genre_id")
    )
    private Set<Genre> genres=new HashSet<>();
}
