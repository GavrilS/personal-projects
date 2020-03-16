package com.telerik.ridepalplaylistgenerator.repository;

import com.telerik.ridepalplaylistgenerator.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre getGenreById(long genreId);

    @Query("SELECT g FROM Genre g WHERE g.enabled = true")
    List<Genre> getEnableGenres();

    Genre getGenreByName(String name);
}
