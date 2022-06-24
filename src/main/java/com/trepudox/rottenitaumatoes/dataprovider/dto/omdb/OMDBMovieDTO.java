package com.trepudox.rottenitaumatoes.dataprovider.dto.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OMDBMovieDTO extends OMDBItemDTO {

    @JsonProperty("DVD")
    private String dvd;

    @JsonProperty("BoxOffice")
    private String boxOffice;

    @JsonProperty("Production")
    private String production;

    @JsonProperty("Website")
    private String website;

}
