package com.trepudox.rottenitaumatoes.dataprovider.enums;

import lombok.Getter;

@Getter
public enum EnVoteType {

    LIKE("like"),
    DISLIKE("dislike");

    final String label;

    EnVoteType(String label) {
        this.label = label;
    }
}
