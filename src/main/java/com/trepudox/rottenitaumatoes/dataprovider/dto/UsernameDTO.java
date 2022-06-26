package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsernameDTO {

    @NotBlank
    private String username;

}
