package com.telerik.ridepalplaylistgenerator.controllers.rest;

import com.telerik.ridepalplaylistgenerator.exceptions.DuplicateEntityException;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.exceptions.GenreDurationException;
import com.telerik.ridepalplaylistgenerator.exceptions.TravelDurationException;
import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.models.Playlist;
import com.telerik.ridepalplaylistgenerator.models.Track;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistGenerationDto;
import com.telerik.ridepalplaylistgenerator.repository.GenreRepository;
import com.telerik.ridepalplaylistgenerator.service.interfaces.GeneratePlaylistService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.PlaylistService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistRestController {
    private PlaylistService playlistService;
    private GeneratePlaylistService generatePlaylistService;
    private GenreRepository genreRepository;

    @Autowired
    public PlaylistRestController(PlaylistService playlistService, GeneratePlaylistService generatePlaylistService,
                                  GenreRepository genreRepository) {
        this.playlistService = playlistService;
        this.generatePlaylistService = generatePlaylistService;
        this.genreRepository = genreRepository;
    }

    @PostMapping
    public Playlist createPlaylist(@RequestParam PlaylistGenerationDto playlistGenerationDto,
                                   @RequestParam int userId) throws JSONException {
        try{
            return playlistService.create(playlistGenerationDto, userId);
        } catch (EntityNotFoundException | TravelDurationException | GenreDurationException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @GetMapping("/playlist/{id}")
    public Playlist getPlaylistById(@PathVariable int id) {
        try {
            return playlistService.getPlaylistById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException dee) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, dee.getMessage());
        }
    }

    @GetMapping("playlist/title/{title}")
    public Playlist getPlaylistByTitle(@PathVariable String title) {
        try {
            return (Playlist) playlistService.getByTitle(title);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException dee) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, dee.getMessage());
        }
    }

    @GetMapping
    public List<Playlist> getAllPlaylist() {
        return playlistService.getAll();
    }

    @PutMapping("playlist/{id}")
    public Playlist updatePlaylist(@PathVariable int id, @RequestBody @Valid Playlist playlist) {
        try {
            return playlistService.updatePlaylist(playlist,id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException dee) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, dee.getMessage());
        }
    }

    @DeleteMapping("playlist/{id}")
    public void deletePlaylist(@PathVariable Integer id) {
        try {
            playlistService.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

//    @DeleteMapping("/user/{id}")
//    public void deletePlaylistByUserId(@PathVariable Integer id){
//        try{
//            playlistService.deleteByUserId(id);
//        }catch(EntityNotFoundException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }

    @GetMapping("userplaylists/{userId}")
    public List<Playlist> getAllUserPlaylist(@PathVariable Integer userId){
        return playlistService.getAllPlaylistByUserId(userId);
    }
}
