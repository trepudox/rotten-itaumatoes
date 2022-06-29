package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVoteReviewWithQuoteDTO {

    @NotNull
    @Min(0)
    private Long reviewWithoQuoteId;

    @NotBlank
    private String voteType;


}
