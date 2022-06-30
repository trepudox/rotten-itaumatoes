package com.trepudox.rottenitaumatoes.dataprovider.enums;

import lombok.Getter;

@Getter
public enum EnReviewType {

    NORMAL_REVIEW("review"),
    REVIEW_WITH_QUOTE("reviewWithQuote");

    final String label;

    EnReviewType(String label) {
        this.label = label;
    }
}
