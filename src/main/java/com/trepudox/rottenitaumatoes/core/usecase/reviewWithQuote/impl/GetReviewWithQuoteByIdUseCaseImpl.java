package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewWithQuoteMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.IGetReviewWithQuoteByIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetReviewWithQuoteByIdUseCaseImpl implements IGetReviewWithQuoteByIdUseCase {

    private final ReviewWithQuoteRepository reviewWithQuoteRepository;

    @Override
    public ReviewWithQuoteDTO get(Long reviewId) {
        ReviewWithQuoteModel reviewWithQuoteModel = reviewWithQuoteRepository.findById(reviewId)
                .orElseThrow(() -> new APIException("Review with quote não existe", "Não foi possível encontrar a review with quote solicitada", 422));
        return ReviewWithQuoteMapper.INSTANCE.reviewWithQuoteModelToReviewWithQuoteDTO(reviewWithQuoteModel);
    }
}
