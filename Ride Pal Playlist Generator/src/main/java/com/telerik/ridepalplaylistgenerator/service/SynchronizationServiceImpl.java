package com.telerik.ridepalplaylistgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.repository.GenreRepository;
import com.telerik.ridepalplaylistgenerator.service.interfaces.DbLoaderService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.SynchronizationService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SynchronizationServiceImpl implements SynchronizationService {

    private GenreRepository genreRepository;
    private DbLoaderService dbLoaderService;

    @Autowired
    public SynchronizationServiceImpl(GenreRepository genreRepository, DbLoaderService dbLoaderService) {
        this.genreRepository = genreRepository;
        this.dbLoaderService = dbLoaderService;
    }

    @Override
    public void synchronizeGenre(long genreId) throws ParseException, JsonProcessingException {
        Genre genre = genreRepository.getGenreById(genreId);
        if(genre == null){
            throw new EntityNotFoundException(String.format("There is no genre with id %d.", genreId));
        }
        dbLoaderService.populateSongsByGenre(genreId);
    }

    @Override
    public void enableGenre(long genreId) {
        Genre genre = genreRepository.getGenreById(genreId);
        if(genre == null){
            throw new EntityNotFoundException(String.format("There is no genre with id %d.", genreId));
        }
        genre.setEnabled(true);
        genreRepository.saveAndFlush(genre);
    }

    @Override
    public void disableGenre(long genreId) {
        Genre genre = genreRepository.getGenreById(genreId);
        if(genre == null){
            throw new EntityNotFoundException(String.format("There is no genre with id %d.", genreId));
        }
        genre.setEnabled(false);
        genreRepository.saveAndFlush(genre);
    }
}
