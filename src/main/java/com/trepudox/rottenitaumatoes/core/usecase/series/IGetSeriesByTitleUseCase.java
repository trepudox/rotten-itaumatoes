package com.trepudox.rottenitaumatoes.core.usecase.series;

import com.trepudox.rottenitaumatoes.dataprovider.dto.SeriesDTO;

public interface IGetSeriesByTitleUseCase {

    SeriesDTO get(String title);

}
