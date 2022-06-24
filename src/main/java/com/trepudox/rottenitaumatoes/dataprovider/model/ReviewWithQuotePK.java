package com.trepudox.rottenitaumatoes.dataprovider.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ReviewWithQuotePK implements Serializable {

    private static final long serialVersionUID = 5191402051867258911L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_with_quote_id")
    private Long reviewWithQuoteId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reviewer")
    private User reviewer;

}
