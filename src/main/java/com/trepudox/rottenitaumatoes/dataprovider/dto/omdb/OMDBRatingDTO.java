package com.trepudox.rottenitaumatoes.dataprovider.dto.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OMDBRatingDTO {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;

}
