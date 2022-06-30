package com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReviewWithQuoteMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.IVoteOnReviewWithQuoteUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateVoteOnReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnVoteType;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.VoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.VoteRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VoteOnReviewWithQuoteUseCaseImpl implements IVoteOnReviewWithQuoteUseCase {

    private final JwtTokenUtil jwtTokenUtil;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ReviewWithQuoteRepository reviewWithQuoteRepository;

    @Override
    public ReviewWithQuoteDTO vote(String token, CreateVoteOnReviewWithQuoteDTO payload) {
        EnVoteType voteType = getVoteType(payload.getVoteType());

        UserModel votingUser = getUserModel(token);

        ReviewWithQuoteModel votedReviewWithQuote = getReviewWithQuoteModel(payload.getReviewWithQuoteId());

        if(votingUser.getUsername().equals(votedReviewWithQuote.getReviewer().getUsername()))
            throw new APIException("Solicitação não atendida", "Usuário não pode votar na própria publicação", 400);

        createAndSaveVote(votingUser, votedReviewWithQuote, voteType);

        return ReviewWithQuoteMapper.INSTANCE.reviewWithQuoteModelToReviewWithQuoteDTO(votedReviewWithQuote);
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

    private ReviewWithQuoteModel getReviewWithQuoteModel(Long reviewWithQuoteId) {
        return reviewWithQuoteRepository.findById(reviewWithQuoteId)
                .orElseThrow(() -> new APIException("Não foi possível avaliar a review with quote", "A review with quote requisitada não existe", 500));
    }

    private void createAndSaveVote(UserModel votingUser, ReviewWithQuoteModel votedReviewWithQuote, EnVoteType voteType) {
        Optional<VoteModel> userPreviousVote = voteRepository.findByVotingUserAndVotedReviewWithQuote(votingUser, votedReviewWithQuote);

        Long previousVoteId = null;
        if(userPreviousVote.isPresent())
            previousVoteId = userPreviousVote.get().getVoteId();

        VoteModel voteModel = VoteModel.builder()
                .voteId(previousVoteId)
                .voteType(voteType)
                .votingUser(votingUser)
                .votedReviewWithQuote(votedReviewWithQuote)
                .build();

        voteRepository.saveAndFlush(voteModel);
    }

}
