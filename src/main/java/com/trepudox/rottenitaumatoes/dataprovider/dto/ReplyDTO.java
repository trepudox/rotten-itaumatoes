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
    private UserDTO replier;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReviewDTO repliedReview;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReviewWithQuoteDTO repliedReviewWithQuote;

    private String text;
    private Long likes;
    private Long dislikes;
    private LocalDateTime updateDateTime;
    private LocalDateTime creationDateTime;

}
