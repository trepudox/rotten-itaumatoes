package com.trepudox.rottenitaumatoes.dataprovider.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OMDBClient", url = "${feign.config.omdb.url}")
public interface OMDBFeignClient {

    //TODO: Alterar tipo do retorno

    @GetMapping
    ResponseEntity<?> getByTitleAndType(@RequestParam("apikey") String apiKey,
                                        @RequestParam("t") String title,
                                        @RequestParam("type") String type);

    @GetMapping
    ResponseEntity<?> getByImdbId(@RequestParam("apikey") String apiKey,
                                  @RequestParam("i") String imdbId);

    //Os searchs retornam um objeto mais simplificado
    @GetMapping
    ResponseEntity<?> searchByTitleAndTypeAndPage(@RequestParam("apikey") String apiKey,
                                                  @RequestParam("s") String title,
                                                  @RequestParam("type") String type,
                                                  @RequestParam("page") int page);

    @GetMapping
    ResponseEntity<?> searchByTitleAndTypeAndYearAndPage(@RequestParam("apikey") String apiKey,
                                                         @RequestParam("s") String title,
                                                         @RequestParam("type") String type,
                                                         @RequestParam("y") String year,
                                                         @RequestParam("page") int page);
}
