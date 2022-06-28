package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewWithQuoteDTO {

    @NotBlank
    private String imdbId;

    @Min(0)
    private Long quotedReviewId;

    @Range(min = 0, max = 10)
    private Double rating;

    @NotBlank
    private String text;

}
