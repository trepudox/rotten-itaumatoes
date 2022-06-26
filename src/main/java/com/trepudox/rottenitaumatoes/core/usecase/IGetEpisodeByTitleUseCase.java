package com.trepudox.rottenitaumatoes.core.usecase;

import com.trepudox.rottenitaumatoes.dataprovider.dto.EpisodeDTO;

public interface IGetEpisodeByTitleUseCase {

    EpisodeDTO get(String title);

}
