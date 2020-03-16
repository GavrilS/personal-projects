package com.telerik.ridepalplaylistgenerator.repository;

import com.telerik.ridepalplaylistgenerator.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album getAlbumByTitle(String title);
}
