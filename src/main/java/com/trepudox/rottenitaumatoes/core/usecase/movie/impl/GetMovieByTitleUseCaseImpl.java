package com.trepudox.rottenitaumatoes.core.usecase.movie.impl;

import com.trepudox.rottenitaumatoes.core.mapper.MovieMapper;
import com.trepudox.rottenitaumatoes.core.usecase.movie.IGetMovieByTitleUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.MovieDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBMovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMovieByTitleUseCaseImpl implements IGetMovieByTitleUseCase {

    private final IOMDBClient omdbClient;

    @Override
    public MovieDTO get(String title) {
        OMDBMovieDTO omdbMovie = omdbClient.getMovieByTitle(title);
        return MovieMapper.INSTANCE.omdbMovieToMovieDTO(omdbMovie);
    }

}
