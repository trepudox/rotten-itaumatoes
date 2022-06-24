package com.trepudox.rottenitaumatoes.dataprovider.dto.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OMDBSeriesDTO extends OMDBItemDTO {

    @JsonProperty("totalSeasons")
    private String totalSeasons;

}
