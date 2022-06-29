package com.trepudox.rottenitaumatoes.core.usecase.reply.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.core.usecase.reply.IDeleteReplyByIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnProfile;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReplyRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteReplyByIdUseCaseImpl implements IDeleteReplyByIdUseCase {

    private static final EnProfile NEEDED_ROLE = EnProfile.MODERADOR;

    private final ReplyRepository replyRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void delete(String token, Long replyId) {
        String replierUsername = retrieveReplierUsername(replyId);

        String jwt = token.replace("Bearer ", "").trim();
        String tokenUsername = jwtTokenUtil.getSubjectFromToken(jwt);
        List<String> roles = jwtTokenUtil.getTokenRoles(jwt);

        checkPermission(replierUsername, tokenUsername, roles);

        replyRepository.deleteById(replyId);
    }

    private String retrieveReplierUsername(Long replyId) {
        ReplyModel replyModel = replyRepository.findById(replyId)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "A reply não existe", 422));

        return replyModel.getReplier().getUsername();
    }

    private void checkPermission(String replierUsername, String tokenUsername, List<String> roles) {
        if(!replierUsername.equals(tokenUsername) && !roles.contains(NEEDED_ROLE.name())) {
            throw new APISecurityException("Acesso negado", "Usuário não possui permissão necessária para acessar esse recurso", 403);
        }
    }

}
