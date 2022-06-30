package com.trepudox.rottenitaumatoes.core.usecase.reply.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReplyMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reply.IVoteOnReplyUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateVoteOnReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnVoteType;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.VoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReplyRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.VoteRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VoteOnReplyUseCaseImpl implements IVoteOnReplyUseCase {

    private final JwtTokenUtil jwtTokenUtil;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    @Override
    public ReplyDTO vote(String token, CreateVoteOnReplyDTO payload) {
        EnVoteType voteType = getVoteType(payload.getVoteType());

        UserModel votingUser = getUserModel(token);

        ReplyModel votedReply = getReplyModel(payload.getReplyId());

        if(votingUser.getUsername().equals(votedReply.getReplier().getUsername()))
            throw new APIException("Solicitação não atendida", "Usuário não pode votar na própria publicação", 400);

        createAndSaveVote(votingUser, votedReply, voteType);

        return ReplyMapper.INSTANCE.replyModelToReplyDTO(votedReply);
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

    private ReplyModel getReplyModel(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> new APIException("Não foi possível avaliar a reply", "A reply requisitada não existe", 500));
    }

    private void createAndSaveVote(UserModel votingUser, ReplyModel votedReply, EnVoteType voteType) {
        Optional<VoteModel> userPreviousVote = voteRepository.findByVotingUserAndVotedReply(votingUser, votedReply);

        Long previouesVoteId = null;
        if(userPreviousVote.isPresent())
            previouesVoteId = userPreviousVote.get().getVoteId();

        VoteModel voteModel = VoteModel.builder()
                .voteId(previouesVoteId)
                .voteType(voteType)
                .votingUser(votingUser)
                .votedReply(votedReply)
                .build();

        voteRepository.saveAndFlush(voteModel);
    }

}
