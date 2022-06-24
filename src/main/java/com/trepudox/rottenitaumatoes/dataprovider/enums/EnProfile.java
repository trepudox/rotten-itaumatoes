package com.trepudox.rottenitaumatoes.dataprovider.enums;

import lombok.Getter;

@Getter
public enum EnProfile {

    LEITOR(0L),
    BASICO(20L),
    AVANCADO(100L),
    MODERADOR(1000L);

    final Long score;

    EnProfile(Long score) {
        this.score = score;
    }
}
