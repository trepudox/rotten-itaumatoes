package com.trepudox.rottenitaumatoes.core.usecase.review;

import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateVoteOnReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;

public interface IVoteOnReviewUseCase {

    ReviewDTO vote(String token, CreateVoteOnReviewDTO payload);

}
