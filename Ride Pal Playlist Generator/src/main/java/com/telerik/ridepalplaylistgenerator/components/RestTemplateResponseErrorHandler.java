package com.telerik.ridepalplaylistgenerator.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response)
            throws IOException {
        return (
                response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                        || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response)
            throws IOException {
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry ! Our API was broken !", new RuntimeException());
        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            throw new IllegalArgumentException("Illegal input for your destination.");
        }
    }
}
