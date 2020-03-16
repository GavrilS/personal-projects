package com.telerik.ridepalplaylistgenerator.repository;

import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Track;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> getTrackByTitle(String title);

    @Query("SELECT t FROM Track t WHERE t.genreId = ?1 AND t.duration <= ?2")
    List<Track> getTracksByGenre(long genreId, int genreDuration);

    @Query(
            value = "SELECT * FROM tracks t WHERE t.genre_id = ?1 AND t.duration <= ?2 ORDER BY t.rank ASC LIMIT 1000",
            nativeQuery = true)
    List<Track> getTracksByGenreAndRank(long genreId, int genreDuration);
}
