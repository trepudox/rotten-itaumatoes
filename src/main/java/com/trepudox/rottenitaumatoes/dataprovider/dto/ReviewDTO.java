package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long reviewId;
    private UserDTO reviewer;
    private String imdbId;
    private Double rating;
    private String text;
    private Long likes;
    private Long dislikes;
    private Boolean duplicated;
    private LocalDateTime updateDateTime;
    private LocalDateTime creationDateTime;

}
