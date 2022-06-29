package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuplicatedReplyDTO {

    @NotNull
    @Min(0L)
    private Long replyId;

    @NotNull
    private Boolean duplicated;

}
