package com.trepudox.rottenitaumatoes.core.usecase.review.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewMapper;
import com.trepudox.rottenitaumatoes.core.usecase.review.IUpdateReviewUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UpdateReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateReviewUseCaseImpl implements IUpdateReviewUseCase {

    private final JwtTokenUtil jwtTokenUtil;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDTO update(String token, UpdateReviewDTO payload) {
        String username = getUsernameFromToken(token);

        ReviewModel reviewModel = retrieveReviewModel(payload.getReviewId());

        if(!username.equals(reviewModel.getReviewer().getUsername()))
            throw new APISecurityException("Acesso negado", "Usuário não possui permissão necessária para acessar esse recurso", 403);

        Double rating = payload.getRating();
        String text = payload.getText();

        checkParameters(rating, text);

        updateReviewModel(reviewModel, rating, text);

        return ReviewMapper.INSTANCE.reviewModelToReviewDTO(reviewModel);
    }

    private String getUsernameFromToken(String token) {
        String jwt = token.replace("Bearer ", "").trim();
        return jwtTokenUtil.getSubjectFromToken(jwt);
    }

    private ReviewModel retrieveReviewModel(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "A review não existe", 422));
    }

    private void checkParameters(Double rating, String text) {
        if(rating == null && (text == null || text.isBlank()))
            throw new APIException("Solicitação não atendida", "Ao menos um dos campos precisam estar preenchidos: 'text', 'rating'", 400);
    }

    private void updateReviewModel(ReviewModel reviewModel, @Nullable Double rating, @Nullable String text) {
        boolean ratingIsNull = rating == null;
        boolean textIsNullOrBlank = text == null || text.isBlank();

        if(!ratingIsNull && !textIsNullOrBlank) {
            reviewModel.setText(text);
            reviewModel.setRating(rating);
        } else if(ratingIsNull && !textIsNullOrBlank) {
            reviewModel.setText(text);
        } else if(!ratingIsNull && textIsNullOrBlank) {
            reviewModel.setRating(rating);
        } else {
            log.error("Parâmetros não estão sendo checado anteriormente!");
            throw new APIException("Não foi possível realizar a operação", "Erro interno no servidor", 500);
        }

        reviewRepository.saveAndFlush(reviewModel);
    }

}
