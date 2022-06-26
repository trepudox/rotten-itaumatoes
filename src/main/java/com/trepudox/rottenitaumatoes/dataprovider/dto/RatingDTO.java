package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

    private String source;
    private String value;

}
