package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {


    private String title;
    private String detail;
    private int status;
    private LocalDateTime dateTime;

}
