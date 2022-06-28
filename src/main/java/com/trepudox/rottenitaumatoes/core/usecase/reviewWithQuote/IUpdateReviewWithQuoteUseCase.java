package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UpdateReviewDTO;

public interface IUpdateReviewWithQuoteUseCase {

    ReviewWithQuoteDTO update(String token, UpdateReviewDTO payload);

}
