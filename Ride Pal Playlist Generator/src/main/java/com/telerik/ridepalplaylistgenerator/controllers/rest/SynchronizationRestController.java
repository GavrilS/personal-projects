package com.telerik.ridepalplaylistgenerator.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.service.interfaces.SynchronizationService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/synchronization")
public class SynchronizationRestController {

    private SynchronizationService synchronizationService;

    @Autowired
    public SynchronizationRestController(SynchronizationService synchronizationService) {
        this.synchronizationService = synchronizationService;
    }

    @RequestMapping("/synchronize/{genreId}")
    public void synchronizeGenre(@PathVariable long genreId) {
        try {
            synchronizationService.synchronizeGenre(genreId);
        } catch (ParseException | JsonProcessingException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping("/enable/{genreId}")
    public void enableGenre(@PathVariable long genreId) {
        try {
            synchronizationService.enableGenre(genreId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping("/disable/{genreId}")
    public void disableGenre(@PathVariable long genreId) {
        try {
            synchronizationService.disableGenre(genreId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
