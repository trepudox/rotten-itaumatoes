package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestDTO {

    private String username;
    private String password;

}
