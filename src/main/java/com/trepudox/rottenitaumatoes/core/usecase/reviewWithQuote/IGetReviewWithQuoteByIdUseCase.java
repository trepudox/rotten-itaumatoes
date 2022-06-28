package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;

public interface IGetReviewWithQuoteByIdUseCase {

    ReviewWithQuoteDTO get(Long reviewId);

}
