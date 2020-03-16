package com.telerik.ridepalplaylistgenerator.repository;

import com.telerik.ridepalplaylistgenerator.models.Playlist;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    @Query(value="SELECT * FROM playlists p where p.playlist_id = ?1",nativeQuery = true)
    Playlist getPlaylistById(int id);

    List<Playlist> findByTitle(String title);

    @Query(
            value = "SELECT * FROM playlists p where p.enabled= true ORDER BY p.rank ASC limit 3",
            nativeQuery = true
    )
    List<Playlist> findAllByOrderByRankAvgAsc();

    @Query("SELECT p FROM Playlist p WHERE p.title=?1 and p.enabled = true")
    List<Playlist> getByTitle(String title);

    @Query(value = "SELECT * FROM playlists p where p.user_id=?1 and p.enabled = true",nativeQuery = true)
    List<Playlist> findAllByUserID(int userId);

    @Query("SELECT p FROM Playlist p WHERE p.title LIKE ?1 and p.enabled = true ORDER BY p.rank ASC")
    List<Playlist> filterByName(String value);

    @Query("SELECT p FROM Playlist p WHERE p.duration <= ?1 and p.enabled = true ORDER BY p.rank ASC")
    List<Playlist> filterByDuration(int duration);
}
