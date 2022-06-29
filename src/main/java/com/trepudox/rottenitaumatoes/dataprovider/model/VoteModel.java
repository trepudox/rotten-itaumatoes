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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vote")
@EntityListeners(AuditingEntityListener.class)
public class VoteModel implements Serializable {

    private static final long serialVersionUID = 8790584860751972585L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_type")
    private EnVoteType voteType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "voting_user")
    private UserModel votingUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "voted_review_id")
    private ReviewModel votedReview;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "voted_review_with_quote_id")
    private ReviewWithQuoteModel votedReviewWithQuote;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "voted_reply_id")
    private ReplyModel votedReply;

    @LastModifiedDate
    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;

    @CreatedDate
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;


}
