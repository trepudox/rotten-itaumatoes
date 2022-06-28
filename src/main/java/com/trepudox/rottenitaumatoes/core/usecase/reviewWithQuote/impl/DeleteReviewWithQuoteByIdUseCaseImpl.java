package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.IDeleteReviewWithQuoteByIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnProfile;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteReviewWithQuoteByIdUseCaseImpl implements IDeleteReviewWithQuoteByIdUseCase {

    private static final EnProfile NEEDED_ROLE = EnProfile.MODERADOR;

    private final ReviewWithQuoteRepository reviewWithQuoteRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void delete(String token, Long reviewWithQuoteId) {
        String reviewerUsername = retrieveReviewerUsername(reviewWithQuoteId);

        String jwt = token.replace("Bearer ", "").trim();
        String tokenUsername = jwtTokenUtil.getSubjectFromToken(jwt);
        List<String> roles = jwtTokenUtil.getTokenRoles(jwt);

        checkPermission(reviewerUsername, tokenUsername, roles);

        reviewWithQuoteRepository.deleteById(reviewWithQuoteId);
    }

    private String retrieveReviewerUsername(Long reviewId) {
        ReviewWithQuoteModel reviewWithQuoteModel = reviewWithQuoteRepository.findById(reviewId)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "A review with quote não existe", 422));

        return reviewWithQuoteModel.getReviewer().getUsername();
    }

    private void checkPermission(String reviewerUsername, String tokenUsername, List<String> roles) {
        if(!reviewerUsername.equals(tokenUsername) && !roles.contains(NEEDED_ROLE.name())) {
            throw new APISecurityException("Acesso negado", "Usuário não possui permissão necessária para acessar esse recurso", 403);
        }
    }

}
