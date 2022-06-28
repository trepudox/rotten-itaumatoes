package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtifactDTO {

    private String fieldName;
    private String description;
    private Object rejectedValue;

}
