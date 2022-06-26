package com.trepudox.rottenitaumatoes.dataprovider.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

    private List<ItemResumeDTO> items;
    private Integer totalResults;
    private Integer page;
    private Boolean hasNextPage;

    public Boolean getHasNextPage() {
        return totalResults - page * 10 > 0;
    }
}
