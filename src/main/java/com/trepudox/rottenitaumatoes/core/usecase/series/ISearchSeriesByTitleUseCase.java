package com.trepudox.rottenitaumatoes.core.usecase.series;

import com.trepudox.rottenitaumatoes.dataprovider.dto.SearchDTO;

public interface ISearchSeriesByTitleUseCase {

    SearchDTO search(String title, int page);

}
