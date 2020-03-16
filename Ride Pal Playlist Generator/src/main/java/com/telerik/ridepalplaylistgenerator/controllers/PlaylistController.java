package com.telerik.ridepalplaylistgenerator.controllers;

import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.exceptions.GenreDurationException;
import com.telerik.ridepalplaylistgenerator.exceptions.TravelDurationException;
import com.telerik.ridepalplaylistgenerator.models.Playlist;
import com.telerik.ridepalplaylistgenerator.models.User;
import com.telerik.ridepalplaylistgenerator.models.dto.GenrePercentageDto;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistGenerationDto;
import com.telerik.ridepalplaylistgenerator.models.dto.PlaylistDto;
import com.telerik.ridepalplaylistgenerator.service.interfaces.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
public class PlaylistController {
    private PlaylistService playlistService;
    private DbLoaderService dbLoader;
    private GeneratePlaylistService generatePlaylistService;
    private LocationService locationService;
    private UserService userService;
    private GenreService genreService;
    private PlaylistTrackService playlistTrackService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, DbLoaderService dbLoader,
                              GeneratePlaylistService generatePlaylistService, LocationService locationService,
                              UserService userService, GenreService genreService,PlaylistTrackService playlistTrackService) {
        this.playlistService = playlistService;
        this.dbLoader = dbLoader;
        this.generatePlaylistService = generatePlaylistService;
        this.locationService = locationService;
        this.userService = userService;
        this.genreService = genreService;
        this.playlistTrackService = playlistTrackService;
    }


    @GetMapping("/playlists")
    public String showPlaylists(Model model) {
        List<Playlist> playlistsOrderedByRank = playlistService.getOrderByRankAsc();
        model.addAttribute("playlists", playlistsOrderedByRank);
        return "playlists-all";
    }

    @GetMapping("/playlists/{playlistId}")
    public String getPlaylistById(Model model, @PathVariable int playlistId) {
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        PlaylistDto playlistDto = PlaylistDto.fromModel(playlist);
        model.addAttribute("playlist", playlistDto);
        model.addAttribute("tracks", playlistTrackService.getTracksByPlaylistId(playlistId));
        return "playlist-single-player";
    }

    @GetMapping("/playlists/create")
    public String createPlaylistForm(Model model) {
        model.addAttribute("activeGenres", genreService.getEnabledGenres());
        model.addAttribute("playlistGen", new PlaylistGenerationDto());
        model.addAttribute("newPlaylist", new PlaylistDto());
        return "create-playlist";
    }

    @PostMapping("/playlists/create")
    public String createPlaylist(@ModelAttribute("playlistGen") PlaylistGenerationDto playlistGeneration,
                                 Principal principal) throws JSONException {
        User loggedUser = userService.getUserByUsername(principal.getName());
        Playlist createdPlaylist = new Playlist();
        try{
            createdPlaylist = playlistService.create(playlistGeneration, loggedUser.getUserId());
        } catch (GenreDurationException e) {
            return "exception";
        }catch (EntityNotFoundException e){
            return "exception";
        } catch (TravelDurationException e){
            return "travel-duration-exception";
        }

        return "redirect:/playlists/" + createdPlaylist.getPlaylistId();
    }

    @GetMapping("/users/username/{username}/{playlistId}/edit")
    @PreAuthorize("authentication.principal.username == #username or hasAuthority('ADMIN')")
    public String updatePlaylistForm(@PathVariable int playlistId,@PathVariable String username, Model model) {
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("playlist", playlist);
        model.addAttribute("newPlaylist", new Playlist());
        return "update-playlist";
    }

    @PostMapping("/users/username/{username}/{playlistId}/update")
    public String updatePlaylist(@PathVariable int playlistId,@PathVariable String username,@RequestParam MultipartFile file, @ModelAttribute("newPlaylist") Playlist newPlaylist,
                                 Model model) throws IOException {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        if (!file.isEmpty()) {
            newPlaylist.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        try{
            playlistService.updatePlaylist(newPlaylist, playlistId);
        } catch (EntityNotFoundException e){
            return "exception";
        }

        return "redirect:/playlists/" + playlistId;
    }


    @RequestMapping("/filterByName")
    public String filterByNameForm(){
        return "filter-by-name";
    }

    @RequestMapping("/playlists/filterByName")
    public String filterPlaylistsByName(Model model, @RequestParam String name) {
        List<Playlist> filteredList = playlistService.filterByName(name);
        model.addAttribute("name", name);
        model.addAttribute("filteredPlaylists", filteredList);
        return "playlists";
    }

    @RequestMapping("/filterByGenre")
    public String filterByGenreForm(){
        return "filter-by-genre";
    }

    @RequestMapping("/playlists/filterByGenre")
    public String filterPlaylistsByGenre(Model model, @RequestParam String genreName){
        List<Playlist> filteredList = playlistService.filterByGenre(genreName);
        model.addAttribute("genreName", genreName);
        model.addAttribute("filteredPlaylists", filteredList);
        return "playlists";
    }

    @RequestMapping("filterByDuration")
    public String filterByDurationForm(){
        return "filter-by-duration";
    }

    @RequestMapping("/playlists/filterByDuration")
    public String filterPlaylistsByDuration(Model model, @RequestParam int duration) {
        List<Playlist> filteredList = playlistService.filterByDuration(duration);
        model.addAttribute("duration", duration);
        model.addAttribute("filteredPlaylists", filteredList);
        return "playlists";
    }

    @GetMapping("/users/username/{username}/playlists")
    @PreAuthorize("authentication.principal.username == #username or hasAuthority('ADMIN')")
    public String showUserPlaylists(@PathVariable String username, Model model) {
        try {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
            model.addAttribute("playlists", playlistService.getAllPlaylistByUserId(user.getUserId()));
            return "user-playlist";
        } catch (SpelEvaluationException see) {
            return "redirect:/access-denied";
        }
    }

    @GetMapping("/users/username/{username}/{playlistId}/delete")
    @PreAuthorize("authentication.principal.username == #username or hasAuthority('ADMIN')")
    public String showPlaylistDeletePage(@PathVariable int playlistId,@PathVariable String username, Model model) {
        try {
            User user=userService.getUserByUsername(username);
            Playlist playlist=playlistService.getPlaylistById(playlistId);
            model.addAttribute("playlist", playlist);
            model.addAttribute("user", user);
            return "playlist-delete";
        } catch (EntityNotFoundException enfe) {
            return "error";
        }
    }

    @PostMapping("/users/username/{username}/{playlistId}/delete")
    @PreAuthorize("authentication.principal.username == #username or hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable int playlistId,@PathVariable String username, Model model) {
        try {
            User user = userService.getUserByUsername(username);
            playlistService.deleteById(playlistId);
            return "playlist-delete-success";
        } catch (EntityNotFoundException enfe) {
            return "error";
        }
    }
}