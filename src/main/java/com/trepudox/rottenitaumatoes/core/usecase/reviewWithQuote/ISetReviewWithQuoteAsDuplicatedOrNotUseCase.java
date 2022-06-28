package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote;

import com.trepudox.rottenitaumatoes.dataprovider.dto.DuplicatedReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;

public interface ISetReviewWithQuoteAsDuplicatedOrNotUseCase {

    ReviewWithQuoteDTO set(DuplicatedReviewDTO duplicatedReview);

}
