package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewWithQuoteMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.ICreateReviewWithQuoteUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.user.IUpdateScoreAndProfileUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.client.IOMDBClient;
import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CreateReviewWithQuoteUseCaseImpl implements ICreateReviewWithQuoteUseCase {

    private final ReviewWithQuoteRepository reviewWithQuoteRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final IOMDBClient omdbClient;
    private final JwtTokenUtil jwtTokenUtil;
    private final IUpdateScoreAndProfileUseCase updateScoreAndProfileUseCase;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReviewWithQuoteDTO create(String token, CreateReviewWithQuoteDTO payload) {
        String imdbId = payload.getImdbId();
        checkIfOMDBItemExists(imdbId);

        ReviewModel reviewModel = getReviewModel(payload.getQuotedReviewId());
        String username = getUsernameFromToken(token);
        UserModel userModel = getUserModel(username);

        ReviewWithQuoteModel reviewWithQuoteModel = createReviewWithQuoteModel(imdbId, userModel, reviewModel, payload.getRating(), payload.getText());

        reviewWithQuoteRepository.save(reviewWithQuoteModel);
        updateScoreAndProfileUseCase.update(username);

        return ReviewWithQuoteMapper.INSTANCE.reviewWithQuoteModelToReviewWithQuoteDTO(reviewWithQuoteModel);
    }

    private void checkIfOMDBItemExists(String imdbId) {
        if(omdbClient.existsByImdbId(imdbId).equals(Boolean.FALSE))
            throw new APIException("Item não existe", "Não foi possível criar a review with quote, o item especificado não existe", 422);
    }

    private ReviewModel getReviewModel(Long quotedReviewId) {
        return reviewRepository.findById(quotedReviewId).orElseThrow(
                () -> new APIException("Item não existe", "Não foi possível criar a review with quote, a review mencionada não existe", 422));
    }

    private String getUsernameFromToken(String token) {
        String jwt = token.replace("Bearer ", "").trim();
        return jwtTokenUtil.getSubjectFromToken(jwt);
    }

    private UserModel getUserModel(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "O usuario da requisição não existe", 500));
    }

    private ReviewWithQuoteModel createReviewWithQuoteModel(String imdbId, UserModel reviewer, ReviewModel quotedReview, Double rating, String text) {
        return ReviewWithQuoteModel.builder()
                .imdbId(imdbId)
                .reviewer(reviewer)
                .quotedReview(quotedReview)
                .rating(rating)
                .text(text)
                .likes(0L).dislikes(0L).duplicated(false)
                .creationDateTime(LocalDateTime.now()).build();
    }

}
