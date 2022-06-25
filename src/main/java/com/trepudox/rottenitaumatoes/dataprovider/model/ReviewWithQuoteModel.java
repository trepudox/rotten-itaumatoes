package com.trepudox.rottenitaumatoes.dataprovider.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review_with_quote")
@EntityListeners(AuditingEntityListener.class)
public class ReviewWithQuote implements Serializable {

    private static final long serialVersionUID = -4628896027142331270L;

    //TODO: analisar cascade, fetchtype e validation

    @EmbeddedId
    private ReviewWithQuotePK primaryKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quoted_review_id")
    @JoinColumn(name = "quoted_reviewer")
    private Review quotedReview;

    @Column(name = "movie_imdb_id")
    private String movieImdbId;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "text")
    private String text;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "dislikes")
    private Long dislikes;

    @CreatedDate
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

}
