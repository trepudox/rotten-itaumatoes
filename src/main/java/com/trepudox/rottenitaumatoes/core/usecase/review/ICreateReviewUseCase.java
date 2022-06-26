package com.trepudox.rottenitaumatoes.core.usecase.review;

import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;

public interface ICreateReviewUseCase {

    ReviewDTO create(String token, CreateReviewDTO body);

}
