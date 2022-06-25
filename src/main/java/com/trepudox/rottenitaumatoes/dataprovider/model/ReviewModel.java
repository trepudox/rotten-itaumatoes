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
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
public class Review implements Serializable {

    private static final long serialVersionUID = 6756969076950547234L;

    //TODO: analisar cascade, fetchtype e validation

    @EmbeddedId
    private ReviewPK primaryKey;

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

    @Column(name = "duplicated")
    private Boolean duplicated;

    @CreatedDate
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

}
