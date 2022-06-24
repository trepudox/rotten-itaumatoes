package com.trepudox.rottenitaumatoes.dataprovider.client;

import org.springframework.http.ResponseEntity;

public interface IOMDBClient {

    //TODO: Alterar tipo do retorno

    ResponseEntity<?> getMovieByTitle(String title);

    ResponseEntity<?> getSeriesByTitle(String title);

    ResponseEntity<?> getByImdbId(String imdbId);

    ResponseEntity<?> searchByTitleAndTypeAndPage(String title, String type, int page);

    ResponseEntity<?> searchMovieByTitleAndPage(String title, int page);

    ResponseEntity<?> searchSeriesByTitleAndPage(String title, int page);

    ResponseEntity<?> searchByTitleAndTypeAndYearAndPage(String title, String type, String year, int page);


}
