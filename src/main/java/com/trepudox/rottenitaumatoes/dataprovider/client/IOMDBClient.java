package com.trepudox.rottenitaumatoes.dataprovider.client;

import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.*;

public interface IOMDBClient {

    OMDBMovieDTO getMovieByTitle(String title);

    OMDBSeriesDTO getSeriesByTitle(String title);

    OMDBEpisodeDTO getEpisodeByTitle(String title);

    OMDBMovieDTO getMovieByImdbId(String imdbId);

    OMDBSeriesDTO getSeriesByImdbId(String imdbId);

    OMDBEpisodeDTO getEpisodeByImdbId(String imdbId);

    OMDBSearchDTO searchMovieByTitleAndPage(String title, int page);

    OMDBSearchDTO searchSeriesByTitleAndPage(String title, int page);

    OMDBSearchDTO searchEpisodeByTitleAndPage(String title, int page);

    OMDBSearchDTO searchByTitleAndTypeAndPage(String title, String type, int page);

    OMDBSearchDTO searchByTitleAndTypeAndYearAndPage(String title, String type, String year, int page);

}
