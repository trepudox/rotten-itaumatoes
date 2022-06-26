package com.trepudox.rottenitaumatoes.core.usecase.review.impl;

import com.trepudox.rottenitaumatoes.core.mapper.ReviewMapper;
import com.trepudox.rottenitaumatoes.core.usecase.review.IGetReviewsByMovieIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetReviewsByMovieIdUseCaseImpl implements IGetReviewsByMovieIdUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> get(String imdbId) {
        List<ReviewModel> reviewModelList = reviewRepository.findAllByImdbId(imdbId);
        return ReviewMapper.INSTANCE.reviewModelListToReviewDTOList(reviewModelList);
    }
}
