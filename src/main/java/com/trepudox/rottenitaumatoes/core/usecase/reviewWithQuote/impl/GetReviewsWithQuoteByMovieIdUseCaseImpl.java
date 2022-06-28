package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.impl;

import com.trepudox.rottenitaumatoes.core.mapper.ReviewWithQuoteMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.IGetReviewsWithQuoteByMovieIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetReviewsWithQuoteByMovieIdUseCaseImpl implements IGetReviewsWithQuoteByMovieIdUseCase {

    private final ReviewWithQuoteRepository reviewWithQuoteRepository;

    @Override
    public List<ReviewWithQuoteDTO> get(String imdbId) {
        List<ReviewWithQuoteModel> reviewWithQuoteModelList = reviewWithQuoteRepository.findAllByImdbId(imdbId);
        return ReviewWithQuoteMapper.INSTANCE.reviewWithQuoteModelListToReviewWithQuoteDTOList(reviewWithQuoteModelList);
    }
}
