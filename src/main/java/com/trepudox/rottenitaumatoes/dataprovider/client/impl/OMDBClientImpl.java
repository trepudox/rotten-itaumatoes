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

    @Value("${feign.config.omdb.apiKey}")
    private String apiKey;

    @Override
    public ResponseEntity<?> getMovieByTitle(String title) {
        return omdbFeignClient.getMovieByTitle(apiKey, title);
    }
}
