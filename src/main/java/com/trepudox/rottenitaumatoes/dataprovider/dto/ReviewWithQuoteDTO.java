package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewWithQuoteDTO {

    private Long reviewWithQuoteId;
    private UserDTO reviewer;
    private ReviewDTO quotedReview;
    private String imdbId;
    private Double rating;
    private String text;
    private Long likes;
    private Long dislikes;
    private Boolean duplicated;
    private LocalDateTime updateDateTime;
    private LocalDateTime creationDateTime;

}
