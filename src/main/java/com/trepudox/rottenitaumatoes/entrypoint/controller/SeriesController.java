package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.IGetSeriesByImdbIdUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.IGetSeriesByTitleUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.ISearchSeriesByTitleUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.impl.GetSeriesByTitleUseCaseImpl;
import com.trepudox.rottenitaumatoes.dataprovider.dto.SearchDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.SeriesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {

    private final IGetSeriesByTitleUseCase getSeriesByTitleUseCase;
    private final IGetSeriesByImdbIdUseCase getSeriesByImdbIdUseCase;
    private final ISearchSeriesByTitleUseCase searchSeriesByTitleUseCase;

    @GetMapping("/title/{title}")
    public ResponseEntity<SeriesDTO> getSeriesByTitle(@PathVariable String title) {
        SeriesDTO series = getSeriesByTitleUseCase.get(title);
        return ResponseEntity.status(HttpStatus.OK).body(series);
    }

    @GetMapping("/imdb-id/{imdbId}")
    public ResponseEntity<SeriesDTO> getSeriesByImdbId(@PathVariable String imdbId) {
        SeriesDTO series = getSeriesByImdbIdUseCase.get(imdbId);
        return ResponseEntity.status(HttpStatus.OK).body(series);
    }

    @GetMapping("/search")
    public ResponseEntity<SearchDTO> searchSeriesByTitle(@RequestParam String title,
                                                         @RequestParam(required = false, defaultValue = "1") int page) {
        SearchDTO series = searchSeriesByTitleUseCase.search(title, page);
        return ResponseEntity.status(HttpStatus.OK).body(series);
    }

}
