package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.mapper.SeriesMapper;
import com.trepudox.rottenitaumatoes.core.usecase.IGetSeriesByTitleUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.SeriesDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBSeriesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSeriesByTitleUseCaseImpl implements IGetSeriesByTitleUseCase {

    private final IOMDBClient omdbClient;

    @Override
    public SeriesDTO get(String title) {
        OMDBSeriesDTO omdbSeries = omdbClient.getSeriesByTitle(title);
        return SeriesMapper.INSTANCE.omdbSeriesToSeriesDTO(omdbSeries);
    }
}
