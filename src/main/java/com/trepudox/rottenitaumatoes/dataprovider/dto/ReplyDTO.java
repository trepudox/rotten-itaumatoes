package com.trepudox.rottenitaumatoes.dataprovider.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Long replyId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReviewModel repliedReview;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReviewWithQuoteModel repliedReviewWithQuote;
    private UserModel replier;
    private String text;
    private Long likes;
    private Long dislikes;
    private LocalDateTime creationDateTime;

}
