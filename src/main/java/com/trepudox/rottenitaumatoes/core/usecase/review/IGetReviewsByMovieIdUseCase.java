package com.trepudox.rottenitaumatoes.core.usecase.review;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;

import java.util.List;

public interface IGetReviewsByMovieIdUseCase {

    List<ReviewDTO> get(String imdbId);

}
