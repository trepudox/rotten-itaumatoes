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
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
public class ReviewModel implements Serializable {

    private static final long serialVersionUID = 6756969076950547234L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "reviewer")
    private UserModel reviewer;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "votedReview")
    private Set<VoteModel> votes = new HashSet<>();

    @Column(name = "likes")
    private Long likes;

    @Column(name = "dislikes")
    private Long dislikes;

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
