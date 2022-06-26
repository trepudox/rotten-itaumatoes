package com.trepudox.rottenitaumatoes.core.usecase.review.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.usecase.review.IDeleteReviewByIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteReviewByIdUseCaseImpl implements IDeleteReviewByIdUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public void delete(Long reviewId) {
        if(!reviewRepository.existsById(reviewId))
            throw new APIException("Solicitação não atendida", "A entidade não existe", 422);

        reviewRepository.deleteById(reviewId);
    }

}
