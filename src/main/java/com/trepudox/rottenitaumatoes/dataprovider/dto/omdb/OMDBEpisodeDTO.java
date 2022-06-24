package com.trepudox.rottenitaumatoes.dataprovider.dto.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OMDBEpisodeDTO extends OMDBItemDTO {

    @JsonProperty("Season")
    private String season;

    @JsonProperty("Episode")
    private String episode;

    private String seriesID;

}
