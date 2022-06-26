package com.trepudox.rottenitaumatoes.core.usecase.impl;

import com.trepudox.rottenitaumatoes.core.mapper.EpisodeMapper;
import com.trepudox.rottenitaumatoes.core.usecase.IGetEpisodeByTitleUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.EpisodeDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBEpisodeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetEpisodeByTitleUseCaseImpl implements IGetEpisodeByTitleUseCase {

    private final IOMDBClient omdbClient;

    @Override
    public EpisodeDTO get(String title) {
        OMDBEpisodeDTO omdbEpisode = omdbClient.getEpisodeByTitle(title);
        return EpisodeMapper.INSTANCE.omdbEpisodeToEpisodeDTO(omdbEpisode);
    }
}
