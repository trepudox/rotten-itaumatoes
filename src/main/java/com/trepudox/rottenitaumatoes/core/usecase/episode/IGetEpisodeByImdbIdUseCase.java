package com.trepudox.rottenitaumatoes.core.usecase.episode;

import com.trepudox.rottenitaumatoes.dataprovider.dto.EpisodeDTO;

public interface IGetEpisodeByImdbIdUseCase {

    EpisodeDTO get(String imdbId);

}
