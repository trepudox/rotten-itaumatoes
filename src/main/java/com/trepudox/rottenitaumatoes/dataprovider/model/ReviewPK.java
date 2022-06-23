package com.trepudox.rottenitaumatoes.dataprovider.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReviewPK implements Serializable {

    private static final long serialVersionUID = -8164948166073342104L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "reviewer")
    private User reviewer;

}
