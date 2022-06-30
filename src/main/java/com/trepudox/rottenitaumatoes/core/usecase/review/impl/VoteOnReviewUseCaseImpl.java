package com.trepudox.rottenitaumatoes.core.usecase.review.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewMapper;
import com.trepudox.rottenitaumatoes.core.usecase.review.IVoteOnReviewUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateVoteOnReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewDTO;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnVoteType;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.VoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.VoteRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VoteOnReviewUseCaseImpl implements IVoteOnReviewUseCase {

    private final JwtTokenUtil jwtTokenUtil;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDTO vote(String token, CreateVoteOnReviewDTO payload) {
        EnVoteType voteType = getVoteType(payload.getVoteType());

        UserModel votingUser = getUserModel(token);

        ReviewModel votedReview = getReviewModel(payload.getReviewId());

        if(votingUser.getUsername().equals(votedReview.getReviewer().getUsername()))
            throw new APIException("Solicitação não atendida", "Usuário não pode votar na própria publicação", 400);

        createAndSaveVote(votingUser, votedReview, voteType);

        return ReviewMapper.INSTANCE.reviewModelToReviewDTO(votedReview);
    }

    private EnVoteType getVoteType(String voteType) {
        if(voteType.toUpperCase().equals(EnVoteType.LIKE.name()))
            return EnVoteType.LIKE;
        else if(voteType.toUpperCase().equals(EnVoteType.DISLIKE.name()))
            return EnVoteType.DISLIKE;

        throw new APIException("Parâmetro inválido", "O parâmetro 'voteType' precisa ser igual a 'LIKE' ou 'DISLIKE'", 400);
    }

    private String getUsernameFromToken(String token) {
        String jwt = token.replace("Bearer ", "").trim();
        return jwtTokenUtil.getSubjectFromToken(jwt);
    }

    private UserModel getUserModel(String token) {
        String username = getUsernameFromToken(token);

        return userRepository.findById(username)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "O usuario da requisição não existe", 500));
    }

    private ReviewModel getReviewModel(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new APIException("Não foi possível avaliar a review", "A review requisitada não existe", 500));
    }

    private void createAndSaveVote(UserModel votingUser, ReviewModel votedReview, EnVoteType voteType) {
        Optional<VoteModel> userPreviousVote = voteRepository.findByVotingUserAndVotedReview(votingUser, votedReview);

        Long previousVoteId = null;
        if(userPreviousVote.isPresent())
            previousVoteId = userPreviousVote.get().getVoteId();

        VoteModel voteModel = VoteModel.builder()
                .voteId(previousVoteId)
                .voteType(voteType)
                .votingUser(votingUser)
                .votedReview(votedReview)
                .build();

        voteRepository.saveAndFlush(voteModel);
    }

}
