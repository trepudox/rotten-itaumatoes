package com.trepudox.rottenitaumatoes.dataprovider.client.impl;

import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.client.feign.OMDBFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OMDBClientImpl implements IOMDBClient {

    private final OMDBFeignClient omdbFeignClient;

    private static final String MOVIE = "movie";
    private static final String SERIES = "series";

    @Value("${feign.config.omdb.apiKey}")
    private String apiKey;

    @Override
    public ResponseEntity<?> getMovieByTitle(String title) {
        return omdbFeignClient.getByTitleAndType(apiKey, title, MOVIE);
    }

    @Override
    public ResponseEntity<?> getSeriesByTitle(String title) {
        return omdbFeignClient.getByTitleAndType(apiKey, title, SERIES);
    }

    @Override
    public ResponseEntity<?> getByImdbId(String imdbId) {
        return omdbFeignClient.getByImdbId(apiKey, imdbId);
    }

    @Override
    public ResponseEntity<?> searchByTitleAndTypeAndPage(String title, String type, int page) {
        return omdbFeignClient.searchByTitleAndTypeAndPage(apiKey, title, type, page);
    }

    @Override
    public ResponseEntity<?> searchMovieByTitleAndPage(String title, int page) {
        return omdbFeignClient.searchByTitleAndTypeAndPage(apiKey, title, MOVIE, page);
    }

    @Override
    public ResponseEntity<?> searchSeriesByTitleAndPage(String title, int page) {
        return omdbFeignClient.searchByTitleAndTypeAndPage(apiKey, title, SERIES, page);
    }

    @Override
    public ResponseEntity<?> searchByTitleAndTypeAndYearAndPage(String title, String type, String year, int page) {
        return omdbFeignClient.searchByTitleAndTypeAndYearAndPage(apiKey, title, type, year, page);
    }
}
