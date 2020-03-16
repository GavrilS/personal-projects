package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.repository.GenreRepository;
import com.telerik.ridepalplaylistgenerator.service.interfaces.DbLoaderService;
import com.telerik.ridepalplaylistgenerator.service.interfaces.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;
    private DbLoaderService dbLoaderService;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, DbLoaderService dbLoaderService) {
        this.genreRepository = genreRepository;
        this.dbLoaderService = dbLoaderService;
    }

    @Override
    public List<Genre> getEnabledGenres() {
        return genreRepository.getEnableGenres();
    }

    @Override
    public List<Genre> getAllGenres(){
        List<Genre> genreList = genreRepository.findAll();
        if(genreList.isEmpty()){
            dbLoaderService.loadInitialGenres();
        }
        return genreRepository.findAll();
    }
}