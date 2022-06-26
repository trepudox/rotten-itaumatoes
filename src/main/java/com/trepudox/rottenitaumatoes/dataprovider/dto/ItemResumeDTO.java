package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResumeDTO {

    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String poster;

}
