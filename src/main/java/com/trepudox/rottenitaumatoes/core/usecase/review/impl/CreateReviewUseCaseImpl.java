package com.trepudox.rottenitaumatoes.core.usecase.review.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewMapper;
import com.trepudox.rottenitaumatoes.core.usecase.review.ICreateReviewUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.user.IUpdateScoreAndProfileUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CreateReviewUseCaseImpl implements ICreateReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final IOMDBClient omdbClient;
    private final JwtTokenUtil jwtTokenUtil;
    private final IUpdateScoreAndProfileUseCase updateScoreAndProfileUseCase;

    @Override
    public ReviewDTO create(String token, CreateReviewDTO payload) {
        String imdbId = payload.getImdbId();

        if(omdbClient.existsByImdbId(imdbId).equals(Boolean.FALSE))
            throw new APIException("Item não existe", "Não foi possível criar a review, o item especificado não existe", 422);

        String jwt = token.replace("Bearer ", "").trim();
        String username = jwtTokenUtil.getSubjectFromToken(jwt);

        UserModel userModel = userRepository.findById(username)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "O usuario da requisição não existe", 500));

        ReviewModel reviewModel = createReviewModel(imdbId, userModel, payload.getRating(), payload.getText());

        reviewRepository.save(reviewModel);

        updateScoreAndProfileUseCase.update(username);

        return ReviewMapper.INSTANCE.reviewModelToReviewDTO(reviewModel);
    }

    private ReviewModel createReviewModel(String imdbId, UserModel reviewer, Double rating, String text) {
        return ReviewModel.builder()
                .imdbId(imdbId)
                .reviewer(reviewer)
                .rating(rating)
                .text(text)
                .likes(0L).dislikes(0L).duplicated(false)
                .creationDateTime(LocalDateTime.now()).build();
    }

}