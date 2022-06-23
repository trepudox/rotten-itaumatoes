package com.trepudox.rottenitaumatoes.dataprovider.client;

import org.springframework.http.ResponseEntity;

public interface IOMDBClient {

    ResponseEntity<?> getMovieByTitle(String title);

}
