package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewWithQuoteMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.IUpdateReviewWithQuoteUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UpdateReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateReviewWithQuoteUseCaseImpl implements IUpdateReviewWithQuoteUseCase {

    private final JwtTokenUtil jwtTokenUtil;
    private final ReviewWithQuoteRepository reviewWithQuoteRepository;

    @Override
    public ReviewWithQuoteDTO update(String token, UpdateReviewDTO payload) {
        String username = getUsernameFromToken(token);

        ReviewWithQuoteModel reviewWithQuoteModel = retrieveReviewWithQuoteModel(payload.getReviewId());

        if(!username.equals(reviewWithQuoteModel.getReviewer().getUsername()))
            throw new APISecurityException("Acesso negado", "Usuário não possui permissão necessária para acessar esse recurso", 403);

        Double rating = payload.getRating();
        String text = payload.getText();

        checkParameters(rating, text);

        updateReviewWithQuoteModel(reviewWithQuoteModel, rating, text);

        return ReviewWithQuoteMapper.INSTANCE.reviewWithQuoteModelToReviewWithQuoteDTO(reviewWithQuoteModel);
    }

    private String getUsernameFromToken(String token) {
        String jwt = token.replace("Bearer ", "").trim();
        return jwtTokenUtil.getSubjectFromToken(jwt);
    }

    private ReviewWithQuoteModel retrieveReviewWithQuoteModel(Long reviewId) {
        return reviewWithQuoteRepository.findById(reviewId)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "A review with quote não existe", 422));
    }

    private void checkParameters(Double rating, String text) {
        if(rating == null && (text == null || text.isBlank()))
            throw new APIException("Solicitação não atendida", "Ao menos um dos campos precisam estar preenchidos: 'text', 'rating'", 400);
    }

    private void updateReviewWithQuoteModel(ReviewWithQuoteModel reviewWithQuoteModel, @Nullable Double rating, @Nullable String text) {
        boolean ratingIsNull = rating == null;
        boolean textIsNullOrBlank = text == null || text.isBlank();

        if(!ratingIsNull && !textIsNullOrBlank) {
            reviewWithQuoteModel.setText(text);
            reviewWithQuoteModel.setRating(rating);
        } else if(ratingIsNull && !textIsNullOrBlank) {
            reviewWithQuoteModel.setText(text);
        } else if(!ratingIsNull && textIsNullOrBlank) {
            reviewWithQuoteModel.setRating(rating);
        } else {
            log.error("Parâmetros não estão sendo checado anteriormente!");
            throw new APIException("Não foi possível realizar a operação", "Erro interno no servidor", 500);
        }

        reviewWithQuoteRepository.saveAndFlush(reviewWithQuoteModel);
    }

}
