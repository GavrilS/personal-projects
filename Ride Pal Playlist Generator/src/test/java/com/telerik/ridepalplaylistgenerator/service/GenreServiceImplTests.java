package com.telerik.ridepalplaylistgenerator.service;

import com.telerik.ridepalplaylistgenerator.Factory;
import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.repository.GenreRepository;
import com.telerik.ridepalplaylistgenerator.service.interfaces.DbLoaderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GenreServiceImplTests {

    @Mock
    GenreRepository genreRepository;

    @Mock
    DbLoaderService dbLoaderService;

    @InjectMocks
    GenreServiceImpl genreService;

    @Test
    public void getAllGenresShould_ReturnGenreList(){
        //Arrange
        Genre genre = Factory.createGenre();
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);
        Mockito.when(genreRepository.findAll())
                .thenReturn(genres);


        //Act
        List<Genre> returnedList = genreService.getAllGenres();

        //Assert
        Assert.assertEquals(genres, returnedList);
    }

    @Test
    public void getEnabledGenresShould_ReturnEnabledGenresInList(){
        //Arrange
        Genre genre = Factory.createGenre();
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);
        Mockito.when(genreRepository.getEnableGenres())
                .thenReturn(genres);

        //Act
        List<Genre> returnedList = genreService.getEnabledGenres();

        //Assert
        Assert.assertEquals(genres, returnedList);
    }
}
