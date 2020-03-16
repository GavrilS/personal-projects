package com.telerik.ridepalplaylistgenerator.repository;

import com.telerik.ridepalplaylistgenerator.models.PlaylistTrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlaylistTrackRepository extends CrudRepository<PlaylistTrack, Integer> {
    List<PlaylistTrack> findAllByPlaylistId(int id);
}
