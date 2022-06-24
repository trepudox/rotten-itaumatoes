package com.trepudox.rottenitaumatoes.dataprovider.dto.omdb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OMDBSearchDTO {

    @JsonProperty("Search")
    private List<OMDBItemResumeDTO> search;

    private String totalResults;

    @JsonProperty("Error")
    private String error;

    @JsonProperty("Response")
    private Boolean response;

}
