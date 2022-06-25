package com.trepudox.rottenitaumatoes.dataprovider.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum EnProfile {

    LEITOR(0L),
    BASICO(20L),
    AVANCADO(100L),
    MODERADOR(1000L);

    private final Long score;

    EnProfile(Long score) {
        this.score = score;
    }

    public static List<String> getRoles(long score) {
        ArrayList<String> roles = new ArrayList<>();
        for(EnProfile profile : EnProfile.values()) {
            if(score >= profile.getScore())
                roles.add(profile.name());
        }

        return roles;
    }

}
