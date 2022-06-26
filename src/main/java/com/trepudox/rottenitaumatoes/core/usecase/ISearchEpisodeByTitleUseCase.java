package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.dto.SearchDTO;

public interface ISearchEpisodeByTitleUseCase {

    SearchDTO search(String title, int page);

}
