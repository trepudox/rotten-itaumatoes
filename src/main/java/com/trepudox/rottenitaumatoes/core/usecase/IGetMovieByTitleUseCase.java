package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.dto.MovieDTO;

public interface IGetMovieByTitleUseCase {

    MovieDTO get(String title);

}
