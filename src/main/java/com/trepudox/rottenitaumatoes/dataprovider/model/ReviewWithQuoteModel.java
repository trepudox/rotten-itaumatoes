package com.trepudox.rottenitaumatoes.dataprovider.model;

import com.trepudox.rottenitaumatoes.dataprovider.enums.EnVoteType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review_with_quote")
@EntityListeners(AuditingEntityListener.class)
public class ReviewWithQuoteModel implements Serializable {

    private static final long serialVersionUID = -4628896027142331270L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_with_quote_id")
    private Long reviewWithQuoteId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "reviewer")
    private UserModel reviewer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "quoted_review_id")
    private ReviewModel quotedReview;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "votedReviewWithQuote")
    private Set<VoteModel> votes;

    @Transient
    private transient Long likes;

    @Transient
    private transient Long dislikes;

    @Column(name = "duplicated")
    private Boolean duplicated;

    @LastModifiedDate
    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;

    @CreatedDate
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    public Long getLikes() {
        if(votes == null)
            return 0L;

        return votes.stream().filter(vote -> vote.getVoteType().equals(EnVoteType.LIKE)).count();
    }

    public Long getDislikes() {
        if(votes == null)
            return 0L;

        return votes.stream().filter(vote -> vote.getVoteType().equals(EnVoteType.DISLIKE)).count();
    }

}
