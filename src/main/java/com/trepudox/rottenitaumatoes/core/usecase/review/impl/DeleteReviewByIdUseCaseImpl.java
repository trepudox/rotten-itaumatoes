package com.trepudox.rottenitaumatoes.core.usecase.review.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.core.usecase.review.IDeleteReviewByIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnProfile;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteReviewByIdUseCaseImpl implements IDeleteReviewByIdUseCase {

    private static final EnProfile NEEDED_ROLE = EnProfile.MODERADOR;

    private final ReviewRepository reviewRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void delete(String token, Long reviewId) {
        String reviewerUsername = retrieveReviewerUsername(reviewId);

        String jwt = token.replace("Bearer ", "").trim();
        String tokenUsername = jwtTokenUtil.getSubjectFromToken(jwt);
        List<String> roles = jwtTokenUtil.getTokenRoles(jwt);

        checkPermission(reviewerUsername, tokenUsername, roles);

        reviewRepository.deleteById(reviewId);
    }

    private String retrieveReviewerUsername(Long reviewId) {
        ReviewModel reviewModel = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "A review não existe", 422));

        return reviewModel.getReviewer().getUsername();
    }

    private void checkPermission(String reviewerUsername, String tokenUsername, List<String> roles) {
        if(!reviewerUsername.equals(tokenUsername) && !roles.contains(NEEDED_ROLE.name())) {
            throw new APISecurityException("Acesso negado", "Usuário não possui permissão necessária para acessar esse recurso", 403);
        }
    }

}
