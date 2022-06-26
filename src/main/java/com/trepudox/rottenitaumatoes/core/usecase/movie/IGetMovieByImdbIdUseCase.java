package com.trepudox.rottenitaumatoes.core.usecase.movie;

import com.trepudox.rottenitaumatoes.dataprovider.dto.MovieDTO;

public interface IGetMovieByImdbIdUseCase {

    MovieDTO get(String imdbId);

}
