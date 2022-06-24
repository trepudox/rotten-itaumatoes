package com.trepudox.rottenitaumatoes.dataprovider.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Reply implements Serializable {

    private static final long serialVersionUID = 959700976255020893L;

    //TODO: analisar cascade, fetchtype e validation

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id")
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "replied_review_id")
    @JoinColumn(name = "replied_username")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Review repliedReview;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "replied_review_with_quote_id")
    @JoinColumn(name = "replied_review_with_quote_username")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReviewWithQuote repliedReviewWithQuote;

    @ManyToOne
    @JoinColumn(name = "replier")
    private User replier;

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
