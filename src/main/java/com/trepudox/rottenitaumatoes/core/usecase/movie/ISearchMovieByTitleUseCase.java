package com.trepudox.rottenitaumatoes.core.usecase.movie;

import com.trepudox.rottenitaumatoes.dataprovider.dto.SearchDTO;


public interface ISearchMovieByTitleUseCase {

    SearchDTO search(String title, int page);

}
