package com.trepudox.rottenitaumatoes.dataprovider.client.feign;

import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBEpisodeDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBMovieDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBSearchDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBSeriesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OMDBClient", url = "${feign.config.omdb.url}")
public interface OMDBFeignClient {

    @GetMapping
    ResponseEntity<OMDBMovieDTO> getMovieByTitle(@RequestParam("apikey") String apiKey,
                                                 @RequestParam("t") String title,
                                                 @RequestParam(value = "type", required = false, defaultValue = "movie") String type);

    @GetMapping
    ResponseEntity<OMDBSeriesDTO> getSeriesByTitle(@RequestParam("apikey") String apiKey,
                                                   @RequestParam("t") String title,
                                                   @RequestParam(value = "type", required = false, defaultValue = "series") String type);

    @GetMapping
    ResponseEntity<OMDBEpisodeDTO> getEpisodeByTitle(@RequestParam("apikey") String apiKey,
                                                     @RequestParam("t") String title,
                                                     @RequestParam(value = "type", required = false, defaultValue = "episode") String type);

    @GetMapping
    ResponseEntity<OMDBMovieDTO> getMovieByImdbId(@RequestParam("apikey") String apiKey,
                                                  @RequestParam("i") String imdbId,
                                                  @RequestParam(value = "type", required = false, defaultValue = "movie") String type);

    @GetMapping
    ResponseEntity<OMDBSeriesDTO> getSeriesByImdbId(@RequestParam("apikey") String apiKey,
                                                    @RequestParam("i") String imdbId,
                                                    @RequestParam(value = "type", required = false, defaultValue = "series") String type);

    @GetMapping
    ResponseEntity<OMDBEpisodeDTO> getEpisodeByImdbId(@RequestParam("apikey") String apiKey,
                                                    @RequestParam("i") String imdbId,
                                                    @RequestParam(value = "type", required = false, defaultValue = "series") String type);

    @GetMapping
    ResponseEntity<OMDBSearchDTO> searchByTitleAndTypeAndPage(@RequestParam("apikey") String apiKey,
                                                              @RequestParam("s") String title,
                                                              @RequestParam("type") String type,
                                                              @RequestParam("page") int page);

    @GetMapping
    ResponseEntity<OMDBSearchDTO> searchByTitleAndTypeAndYearAndPage(@RequestParam("apikey") String apiKey,
                                                                     @RequestParam("s") String title,
                                                                     @RequestParam("type") String type,
                                                                     @RequestParam("y") String year,
                                                                     @RequestParam("page") int page);
}
