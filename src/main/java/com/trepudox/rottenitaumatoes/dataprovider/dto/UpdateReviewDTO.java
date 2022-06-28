package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewDTO {

    @Min(0)
    private Long reviewId;

    @Range(min = 0, max = 10)
    private Double rating;

    private String text;

}
