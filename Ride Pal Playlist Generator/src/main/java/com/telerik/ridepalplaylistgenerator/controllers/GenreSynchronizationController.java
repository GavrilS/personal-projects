package com.telerik.ridepalplaylistgenerator.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telerik.ridepalplaylistgenerator.service.interfaces.GenreService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.SynchronizationService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenreSynchronizationController {
    private GenreService genreService;
    private SynchronizationService synchronizationService;

    @Autowired
    public GenreSynchronizationController(GenreService genreService, SynchronizationService synchronizationService) {
        this.genreService = genreService;
        this.synchronizationService = synchronizationService;
    }

    @GetMapping("/genres")
//    @PreAuthorize("authentication.principal.username ==hasAuthority('ADMIN')")
    public String showAllGenres(Model model){
        model.addAttribute("genres", genreService.getAllGenres());
        return "genres-all";
    }

    @RequestMapping("/synchronize/{genreId}")
//    @PreAuthorize("authentication.principal.username ==hasAuthority('ADMIN')")
    public String synchronizeGenre(@PathVariable long genreId) throws ParseException, JsonProcessingException {
        synchronizationService.synchronizeGenre(genreId);
        return "redirect:/genres";
    }

    @RequestMapping("/disable/{genreId}")
//    @PreAuthorize("authentication.principal.username ==hasAuthority('ADMIN')")
    public String disableGenre(@PathVariable long genreId){
        synchronizationService.disableGenre(genreId);
        return "redirect:/genres";
    }
}
