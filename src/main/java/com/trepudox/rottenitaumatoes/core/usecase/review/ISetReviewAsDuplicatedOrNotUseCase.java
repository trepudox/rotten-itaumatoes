package com.trepudox.rottenitaumatoes.core.usecase.review;

import com.trepudox.rottenitaumatoes.dataprovider.dto.DuplicatedReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;

public interface ISetReviewAsDuplicatedOrNotUseCase {

    ReviewDTO set(DuplicatedReviewDTO duplicatedReview);

}
