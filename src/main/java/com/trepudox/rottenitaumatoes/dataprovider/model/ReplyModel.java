package com.trepudox.rottenitaumatoes.dataprovider.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = "reply")
@EntityListeners(AuditingEntityListener.class)
public class ReplyModel implements Serializable {

    private static final long serialVersionUID = 959700976255020893L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id")
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "replied_review_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReviewModel repliedReview;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "replied_review_with_quote_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReviewWithQuoteModel repliedReviewWithQuote;

    @ManyToOne
    @JoinColumn(name = "replier")
    private UserModel replier;

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
