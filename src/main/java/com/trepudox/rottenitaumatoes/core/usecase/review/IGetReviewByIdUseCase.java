package com.trepudox.rottenitaumatoes.core.usecase.review;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;

public interface IGetReviewByIdUseCase {

    ReviewDTO get(Long reviewId);

}
