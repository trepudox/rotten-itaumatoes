package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote;

import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;

public interface ICreateReviewWithQuoteUseCase {

    ReviewWithQuoteDTO create(String token, CreateReviewWithQuoteDTO payload);

}
