package com.trepudox.rottenitaumatoes.dataprovider.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = "reply")
@EntityListeners(AuditingEntityListener.class)
public class ReplyModel implements Serializable {

    private static final long serialVersionUID = 959700976255020893L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id")
    private Long replyId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "replier")
    private UserModel replier;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "replied_review_id")
    private ReviewModel repliedReview;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "replied_review_with_quote_id")
    private ReviewWithQuoteModel repliedReviewWithQuote;

    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "votedReply")
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
