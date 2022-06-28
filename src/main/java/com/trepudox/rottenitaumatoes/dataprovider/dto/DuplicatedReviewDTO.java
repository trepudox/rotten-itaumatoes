package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuplicatedReviewDTO {

    @NotNull
    @Min(0L)
    private Long reviewId;

    @NotNull
    private Boolean duplicated;

}
