package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.dto.MovieDTO;

public interface IGetMovieByImdbIdUseCase {

    MovieDTO get(String imdbId);

}
