package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBMovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final IOMDBClient omdbClient;

    @GetMapping("/title/{title}")
    public ResponseEntity<OMDBMovieDTO> getMovieByTitle(@PathVariable String title) {
        OMDBMovieDTO movie = omdbClient.getMovieByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

}
