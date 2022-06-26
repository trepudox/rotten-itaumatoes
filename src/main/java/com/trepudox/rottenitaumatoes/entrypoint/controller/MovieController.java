package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.movie.IGetMovieByImdbIdUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.movie.IGetMovieByTitleUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.movie.ISearchMovieByTitleUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.MovieDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final IGetMovieByTitleUseCase getMovieByTitleUseCase;
    private final IGetMovieByImdbIdUseCase getMovieByImdbIdUseCase;
    private final ISearchMovieByTitleUseCase searchMovieByTitleUseCase;

    @GetMapping("/title/{title}")
    public ResponseEntity<MovieDTO> getMovieByTitle(@PathVariable String title) {
        MovieDTO movie = getMovieByTitleUseCase.get(title);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @GetMapping("/imdb-id/{imdbId}")
    public ResponseEntity<MovieDTO> getMovieByImdbId(@PathVariable String imdbId) {
        MovieDTO movie = getMovieByImdbIdUseCase.get(imdbId);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @GetMapping("/search")
    public ResponseEntity<SearchDTO> searchMovieByTitle(@RequestParam String title,
                                                        @RequestParam(required = false, defaultValue = "1") int page) {
        SearchDTO movies = searchMovieByTitleUseCase.search(title, page);
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

}
