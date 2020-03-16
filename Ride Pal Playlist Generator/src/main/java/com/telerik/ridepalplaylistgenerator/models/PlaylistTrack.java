package com.telerik.ridepalplaylistgenerator.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "tracks_playlists")
public class PlaylistTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_playlist_id")
    private int id;

    @Column(name = "playlist_id")
    private int playlistId;

    @Column(name = "track_id")
    private Long trackId;

}
