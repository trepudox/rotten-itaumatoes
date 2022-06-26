package com.trepudox.rottenitaumatoes.core.usecase.review.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewMapper;
import com.trepudox.rottenitaumatoes.core.usecase.review.ISetReviewAsDuplicatedOrNotUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.DuplicatedReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetReviewAsDuplicatedOrNotUseCaseImpl implements ISetReviewAsDuplicatedOrNotUseCase {

    private final ReviewRepository reviewRepository;

    public ReviewDTO set(DuplicatedReviewDTO duplicatedReview) {
        ReviewModel reviewModel = reviewRepository.findById(duplicatedReview.getReviewId()).orElseThrow();
        Boolean boolToSet = duplicatedReview.getDuplicated();

        if(reviewModel.getDuplicated().equals(boolToSet))
            throw new APIException("Solicitação não atendida", String.format("Campo 'duplicated' já está marcado como '%s'", boolToSet), 422);

        reviewModel.setDuplicated(boolToSet);
        reviewRepository.saveAndFlush(reviewModel);

        return ReviewMapper.INSTANCE.reviewModelToReviewDTO(reviewModel);
    }

}
