package com.telerik.ridepalplaylistgenerator.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;

public interface SynchronizationService {

    void synchronizeGenre(long genreId) throws ParseException, JsonProcessingException;

    void enableGenre(long genreId);

    void disableGenre(long genreId);
}
