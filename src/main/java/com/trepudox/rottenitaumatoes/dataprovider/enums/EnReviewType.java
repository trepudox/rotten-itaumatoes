package com.trepudox.rottenitaumatoes.dataprovider.enums;

import lombok.Getter;

@Getter
public enum EnReviewType {

    NORMAL("review"),
    WITH_QUOTE("reviewWithQuote");

    String label;

    EnReviewType(String label) {
        this.label = label;
    }
}
