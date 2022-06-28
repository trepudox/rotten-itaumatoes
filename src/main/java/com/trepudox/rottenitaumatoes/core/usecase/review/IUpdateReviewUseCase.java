package com.trepudox.rottenitaumatoes.core.usecase.review;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UpdateReviewDTO;

public interface IUpdateReviewUseCase {

    ReviewDTO update(String token, UpdateReviewDTO payload);

}
