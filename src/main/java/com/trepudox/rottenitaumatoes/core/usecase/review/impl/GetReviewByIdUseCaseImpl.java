package com.trepudox.rottenitaumatoes.core.usecase.review.impl;

import com.trepudox.rottenitaumatoes.core.mapper.ReviewMapper;
import com.trepudox.rottenitaumatoes.core.usecase.review.IGetReviewByIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetReviewByIdUseCaseImpl implements IGetReviewByIdUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDTO get(Long reviewId) {
        ReviewModel reviewModel = reviewRepository.findById(reviewId).orElseThrow();
        return ReviewMapper.INSTANCE.reviewModelToReviewDTO(reviewModel);
    }
}
