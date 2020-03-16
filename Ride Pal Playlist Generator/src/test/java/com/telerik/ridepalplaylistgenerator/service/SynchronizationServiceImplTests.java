package com.telerik.ridepalplaylistgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telerik.ridepalplaylistgenerator.Factory;
import com.telerik.ridepalplaylistgenerator.exceptions.EntityNotFoundException;
import com.telerik.ridepalplaylistgenerator.models.Genre;
import com.telerik.ridepalplaylistgenerator.repository.GenreRepository;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.jsf.FacesContextUtils;

@RunWith(MockitoJUnitRunner.class)
public class SynchronizationServiceImplTests {

    @Mock
    GenreRepository genreRepository;

    @Mock
    DbLoaderServiceImpl dbLoaderService;

    @InjectMocks
    SynchronizationServiceImpl synchronizationService;

    @Test(expected = EntityNotFoundException.class)
    public void synchronizeGenreShould_ThrowException_When_GenreDoesNotExist() throws ParseException, JsonProcessingException {
        //Assert
        Mockito.when(genreRepository.getGenreById(Mockito.anyLong()))
                .thenReturn(null);

        //Act, Assert
        synchronizationService.synchronizeGenre(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void enableGenreShould_Throw_When_GenreDoesNotExist() {
        //Assert
        Mockito.when(genreRepository.getGenreById(Mockito.anyLong()))
                .thenReturn(null);

        //Act, Assert
        synchronizationService.enableGenre(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void disableGenreShould_ThrowException_When_GenreDoesNotExist() {
        //Assert
        Mockito.when(genreRepository.getGenreById(Mockito.anyLong()))
                .thenReturn(null);

        //Act, Assert
        synchronizationService.disableGenre(1);
    }

    @Test
    public void synchronizeGenreShould_CallDbLoaderServiceImpl() throws ParseException, JsonProcessingException {
        //Arrange
        Genre genre = Factory.createGenre();
        Mockito.when(genreRepository.getGenreById(Mockito.anyLong()))
                .thenReturn(genre);

        //Act
        synchronizationService.synchronizeGenre(genre.getId());

        //Assert
        Mockito.verify(dbLoaderService, Mockito.times(1)).populateSongsByGenre(genre.getId());
    }

    @Test
    public void enableGenreShould_CallGenreRepository(){
        //Arrange
        Genre genre = Factory.createGenre();
        Mockito.when(genreRepository.getGenreById(Mockito.anyLong()))
                .thenReturn(genre);

        //Act
        synchronizationService.enableGenre(genre.getId());

        //Assert
        Mockito.verify(genreRepository, Mockito.times(1)).saveAndFlush(genre);
    }

    @Test
    public void disableGenreShould_CallGenreRepository(){
        //Arrange
        Genre genre = Factory.createGenre();
        Mockito.when(genreRepository.getGenreById(Mockito.anyLong()))
                .thenReturn(genre);

        //Act
        synchronizationService.disableGenre(genre.getId());

        //Assert
        Mockito.verify(genreRepository, Mockito.times(1)).saveAndFlush(genre);
    }
}
