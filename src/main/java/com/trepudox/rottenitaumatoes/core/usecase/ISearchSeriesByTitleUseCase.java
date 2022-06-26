package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.dto.SearchDTO;

public interface ISearchSeriesByTitleUseCase {

    SearchDTO search(String title, int page);

}
