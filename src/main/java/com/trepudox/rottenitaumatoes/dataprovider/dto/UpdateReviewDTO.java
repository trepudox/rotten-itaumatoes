package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewDTO {

    @NotNull
    @Min(0)
    private Long reviewId;

    @Range(min = 0, max = 10)
    private Double rating;

    private String text;

}
