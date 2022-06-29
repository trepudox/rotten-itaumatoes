package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote;

import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateVoteOnReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;

public interface IVoteOnReviewWithQuoteUseCase {

    ReviewWithQuoteDTO vote(String token, CreateVoteOnReviewWithQuoteDTO payload);

}
