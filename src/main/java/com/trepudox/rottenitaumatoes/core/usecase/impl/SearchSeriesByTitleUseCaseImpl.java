package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.mapper.SearchMapper;
import com.trepudox.rottenitaumatoes.core.usecase.ISearchSeriesByTitleUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.SearchDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBSearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SearchSeriesByTitleUseCaseImpl implements ISearchSeriesByTitleUseCase {

    private final IOMDBClient omdbClient;

    @Override
    public SearchDTO search(String title, int page) {
        OMDBSearchDTO omdbSearch = omdbClient.searchSeriesByTitleAndPage(title, page);
        SearchDTO search = SearchMapper.INSTANCE.omdbSearchToSearchDTO(omdbSearch);
        search.setPage(page);

        return search;
    }
}
