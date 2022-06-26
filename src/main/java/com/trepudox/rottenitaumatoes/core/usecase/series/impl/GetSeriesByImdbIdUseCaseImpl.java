package com.trepudox.rottenitaumatoes.core.usecase.series.impl;

import com.trepudox.rottenitaumatoes.core.mapper.SeriesMapper;
import com.trepudox.rottenitaumatoes.core.usecase.series.IGetSeriesByImdbIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.SeriesDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBSeriesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSeriesByImdbIdUseCaseImpl implements IGetSeriesByImdbIdUseCase {

    private final IOMDBClient omdbClient;

    @Override
    public SeriesDTO get(String imdbId) {
        OMDBSeriesDTO omdbSeries = omdbClient.getSeriesByImdbId(imdbId);
        return SeriesMapper.INSTANCE.omdbSeriesToSeriesDTO(omdbSeries);
    }
}
