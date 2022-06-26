package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.episode.IGetEpisodeByImdbIdUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.episode.IGetEpisodeByTitleUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.episode.ISearchEpisodeByTitleUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.EpisodeDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/episodes")
@RequiredArgsConstructor
public class EpisodeController {

    private final IGetEpisodeByTitleUseCase getEpisodeByTitleUseCase;
    private final IGetEpisodeByImdbIdUseCase getEpisodeByImdbIdUseCase;
    private final ISearchEpisodeByTitleUseCase searchEpisodeByTitleUseCase;

    //TODO: analisar funcionamento
    @GetMapping("/title/{title}")
    public ResponseEntity<EpisodeDTO> getEpisodeByTitle(@PathVariable String title) {
        EpisodeDTO episode = getEpisodeByTitleUseCase.get(title);
        return ResponseEntity.status(HttpStatus.OK).body(episode);
    }

    //TODO: analisar funcionamento -- após testes apenas essa consulta funciona
    @GetMapping("/imdb-id/{imdbId}")
    public ResponseEntity<EpisodeDTO> getEpisodeByImdbId(@PathVariable String imdbId) {
        EpisodeDTO episode = getEpisodeByImdbIdUseCase.get(imdbId);
        return ResponseEntity.status(HttpStatus.OK).body(episode);
    }

    //TODO: analisar funcionamento
    @GetMapping("/search")
    public ResponseEntity<SearchDTO> searchEpisodeByTitle(@RequestParam String title,
                                                          @RequestParam(required = false, defaultValue = "1") int page) {
        SearchDTO episodes = searchEpisodeByTitleUseCase.search(title, page);
        return ResponseEntity.status(HttpStatus.OK).body(episodes);
    }

}
