package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;

import java.util.List;

public interface IGetReviewsWithQuoteByMovieIdUseCase {

    List<ReviewWithQuoteDTO> get(String imdbId);

}
