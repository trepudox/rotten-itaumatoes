package com.trepudox.rottenitaumatoes.core.usecase.episode.impl;

import com.trepudox.rottenitaumatoes.core.mapper.EpisodeMapper;
import com.trepudox.rottenitaumatoes.core.usecase.episode.IGetEpisodeByImdbIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.EpisodeDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBEpisodeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetEpisodeByImdbIdUseCaseImpl implements IGetEpisodeByImdbIdUseCase {

    private final IOMDBClient omdbClient;

    @Override
    public EpisodeDTO get(String imdbId) {
        OMDBEpisodeDTO omdbEpisode = omdbClient.getEpisodeByImdbId(imdbId);
        return EpisodeMapper.INSTANCE.omdbEpisodeToEpisodeDTO(omdbEpisode);
    }
}
