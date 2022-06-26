package com.trepudox.rottenitaumatoes.dataprovider.client.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.client.feign.OMDBFeignClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OMDBClientImpl implements IOMDBClient {

    private final OMDBFeignClient omdbFeignClient;

    private static final String MOVIE = "movie";
    private static final String SERIES = "series";
    private static final String EPISODE = "episode";

    private static final String INTEGRATION_ERROR_TITLE = "Houve um erro de integração com a OMDB API";
    private static final String INTEGRATION_ERROR_DETAIL = "Corpo de resposta ausente";
    private static final String OMDB_API_ERROR_TITLE = "Não foi possível realizar a consulta desejada";
    private static final String OMDB_API_ERROR_DETAIL = "Erro retornado da OMDB API: '%s'";
    private static final String NO_ITEMS_FOUND_TITLE = "Nenhum item foi encontrado";
    private static final String NO_ITEMS_FOUND_DETAIL = "A busca não retornou resultados, verifique os parâmetros enviados";
    private static final String OTHER_CATEGORY_TITLE = "ID de outra categoria";
    private static final String SEPARATOR = ". ";
    private static final String PARAMS = "Params: ";

    @Value("${feign.config.omdb.apiKey}")
    private String apiKey;

    @Override
    public OMDBMovieDTO getMovieByTitle(String title) {
        OMDBMovieDTO responseBody = omdbFeignClient.getMovieByTitle(apiKey, title.trim(), MOVIE).getBody();
        handleResponse(responseBody, title);

        return responseBody;
    }

    @Override
    public OMDBSeriesDTO getSeriesByTitle(String title) {
        OMDBSeriesDTO responseBody = omdbFeignClient.getSeriesByTitle(apiKey, title.trim(), SERIES).getBody();
        handleResponse(responseBody, title);

        return responseBody;
    }

    @Override
    public OMDBEpisodeDTO getEpisodeByTitle(String title) {
        OMDBEpisodeDTO responseBody = omdbFeignClient.getEpisodeByTitle(apiKey, title.trim(), EPISODE).getBody();
        handleResponse(responseBody, title);

        return responseBody;
    }

    @Override
    public OMDBMovieDTO getMovieByImdbId(String imdbId) {
        OMDBMovieDTO responseBody = omdbFeignClient.getMovieByImdbId(apiKey, imdbId.trim(), MOVIE).getBody();
        handleResponse(responseBody, imdbId);

        if(!responseBody.getType().equals(MOVIE))
            throw new APIException(OTHER_CATEGORY_TITLE, "Este ID não pertence a um filme", 422);

        return responseBody;
    }

    @Override
    public OMDBSeriesDTO getSeriesByImdbId(String imdbId) {
        OMDBSeriesDTO responseBody = omdbFeignClient.getSeriesByImdbId(apiKey, imdbId.trim(), SERIES).getBody();
        handleResponse(responseBody, imdbId);

        if(!responseBody.getType().equals(SERIES))
            throw new APIException(OTHER_CATEGORY_TITLE, "Este ID não pertence a uma serie", 422);

        return responseBody;
    }

    @Override
    public OMDBEpisodeDTO getEpisodeByImdbId(String imdbId) {
        OMDBEpisodeDTO responseBody = omdbFeignClient.getEpisodeByImdbId(apiKey, imdbId.trim(), EPISODE).getBody();
        handleResponse(responseBody, imdbId);

        if(!responseBody.getType().equals(EPISODE))
            throw new APIException(OTHER_CATEGORY_TITLE, "Este ID não pertence a um episodio", 422);

        return responseBody;
    }

    @Override
    public OMDBSearchDTO searchMovieByTitleAndPage(String title, int page) {
        OMDBSearchDTO responseBody = omdbFeignClient.searchByTitleAndTypeAndPage(apiKey, title.trim(), MOVIE, page).getBody();
        handleResponse(responseBody, title, String.valueOf(page));

        return responseBody;
    }

    @Override
    public OMDBSearchDTO searchSeriesByTitleAndPage(String title, int page) {
        OMDBSearchDTO responseBody = omdbFeignClient.searchByTitleAndTypeAndPage(apiKey, title.trim(), SERIES, page).getBody();
        handleResponse(responseBody, title, String.valueOf(page));

        return responseBody;
    }

    @Override
    public OMDBSearchDTO searchEpisodeByTitleAndPage(String title, int page) {
        OMDBSearchDTO responseBody = omdbFeignClient.searchByTitleAndTypeAndPage(apiKey, title.trim(), EPISODE, page).getBody();
        handleResponse(responseBody, title, String.valueOf(page));

        return responseBody;
    }

    @Override
    public OMDBSearchDTO searchByTitleAndTypeAndPage(String title, String type, int page) {
        OMDBSearchDTO responseBody = omdbFeignClient.searchByTitleAndTypeAndPage(apiKey, title.trim(), type, page).getBody();
        handleResponse(responseBody, title, type, String.valueOf(page));

        return responseBody;
    }

    @Override
    public OMDBSearchDTO searchByTitleAndTypeAndYearAndPage(String title, String type, String year, int page) {
        OMDBSearchDTO responseBody = omdbFeignClient.searchByTitleAndTypeAndYearAndPage(apiKey, title.trim(), type, year, page).getBody();
        handleResponse(responseBody, title, type, year, String.valueOf(page));

        return responseBody;
    }

    private void handleResponse(OMDBItemDTO responseBody, String... params) {
        List<String> printableParams = List.of(params);

        boolean errorFound = false;
        String title = "";
        String detail = "";
        int status = 0;

        if(responseBody == null) {
            title = INTEGRATION_ERROR_TITLE;
            detail = INTEGRATION_ERROR_DETAIL;
            status = 500;

            errorFound = true;
            log.error(INTEGRATION_ERROR_TITLE.concat(SEPARATOR).concat(detail).concat(SEPARATOR).concat(PARAMS).concat(printableParams.toString()));
        } else if(responseBody.getResponse().equals(Boolean.FALSE)) {
            title = OMDB_API_ERROR_TITLE;
            detail = String.format(OMDB_API_ERROR_DETAIL, responseBody.getError());
            status = 422;

            errorFound = true;
            log.error(INTEGRATION_ERROR_TITLE.concat(SEPARATOR).concat(detail).concat(SEPARATOR).concat(PARAMS).concat(printableParams.toString()));
        }

        if(errorFound)
            throw new APIException(title, detail, status);
    }

    private void handleResponse(OMDBSearchDTO responseBody, String... params) {
        List<String> printableParams = List.of(params);

        boolean errorFound = false;
        String title = "";
        String detail = "";
        int status = 0;

        if(responseBody == null) {
            title = INTEGRATION_ERROR_TITLE;
            detail = INTEGRATION_ERROR_DETAIL;
            status = 500;

            errorFound = true;
            log.error(title.concat(SEPARATOR).concat(detail).concat(SEPARATOR).concat(PARAMS).concat(printableParams.toString()));
        } else if(responseBody.getResponse().equals(Boolean.FALSE)) {

            if(responseBody.getError().endsWith("not found!")) {
                log.warn("Pesquisa não retornou resultado".concat(SEPARATOR).concat(PARAMS).concat(printableParams.toString()));
//                responseBody.setSearch(new ArrayList<>());
//                responseBody.setTotalResults("0");
//                responseBody.setResponse(true);
//                responseBody.setError(null);
                throw new APIException(NO_ITEMS_FOUND_TITLE, NO_ITEMS_FOUND_DETAIL, 422);
            } else if(responseBody.getError().startsWith("Too many results")) {
                title = INTEGRATION_ERROR_TITLE;
                detail = "O limite de itens encontrado pela OMDB API foi excedido, por favor, faça uma busca mais específica.";
                status = 422;

                errorFound = true;
            } else {
                title = "Erro interno no servidor";
                detail = "Um erro não esperado foi encontrado";
                status = 500;

                errorFound = true;
                log.error("Não foi possível realizar uma busca na OMDB API. Mensagem retornada: {}. Params: {}",
                        responseBody.getError(), printableParams);
            }
        }

        if(errorFound)
            throw new APIException(title, detail, status);
    }
}
