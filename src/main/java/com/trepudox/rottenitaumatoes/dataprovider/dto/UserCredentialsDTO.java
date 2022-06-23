package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDTO {

    private String username;
    private String password;

}
