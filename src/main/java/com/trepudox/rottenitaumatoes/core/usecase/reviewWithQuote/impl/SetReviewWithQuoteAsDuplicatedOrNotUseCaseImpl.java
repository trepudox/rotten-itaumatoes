package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewWithQuoteMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.ISetReviewWithQuoteAsDuplicatedOrNotUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.DuplicatedReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetReviewWithQuoteAsDuplicatedOrNotUseCaseImpl implements ISetReviewWithQuoteAsDuplicatedOrNotUseCase {

    private final ReviewWithQuoteRepository reviewWithQuoteRepository;

    public ReviewWithQuoteDTO set(DuplicatedReviewDTO duplicatedReview) {
        ReviewWithQuoteModel reviewWithQuoteModel = retrieveReviewWithQuoteModel(duplicatedReview.getReviewId());
        Boolean boolToSet = duplicatedReview.getDuplicated();

        checkSentParameter(reviewWithQuoteModel, boolToSet);

        reviewWithQuoteModel.setDuplicated(boolToSet);
        reviewWithQuoteRepository.saveAndFlush(reviewWithQuoteModel);

        return ReviewWithQuoteMapper.INSTANCE.reviewWithQuoteModelToReviewWithQuoteDTO(reviewWithQuoteModel);
    }

    private ReviewWithQuoteModel retrieveReviewWithQuoteModel(Long reviewId) {
        return reviewWithQuoteRepository.findById(reviewId)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "A review with quote não existe", 422));
    }

    private void checkSentParameter(ReviewWithQuoteModel reviewModel, Boolean boolToSet) {
        if(reviewModel.getDuplicated().equals(boolToSet))
            throw new APIException("Solicitação não atendida", String.format("Campo 'duplicated' já está marcado como '%s'", boolToSet), 422);
    }

}
