package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.mapper.MovieMapper;
import com.trepudox.rottenitaumatoes.core.usecase.IGetMovieByImdbIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.MovieDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBMovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMovieByImdbIdUseCaseImpl implements IGetMovieByImdbIdUseCase {

    private final IOMDBClient omdbClient;

    @Override
    public MovieDTO get(String imdbId) {
        OMDBMovieDTO omdbMovie = omdbClient.getMovieByImdbId(imdbId);
        return MovieMapper.INSTANCE.omdbMovieToMovieDTO(omdbMovie);
    }
}
