package com.trepudox.rottenitaumatoes.dataprovider.dto.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OMDBItemResumeDTO {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    private String imdbID;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Poster")
    private String poster;

}
