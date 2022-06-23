package com.trepudox.rottenitaumatoes.dataprovider.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OMDBClient", url = "${feign.config.omdb.url}")
public interface OMDBFeignClient {

    @GetMapping
    ResponseEntity<?> getMovieByTitle(@RequestParam("apikey") String apiKey,
                                      @RequestParam("t") String title);

}
