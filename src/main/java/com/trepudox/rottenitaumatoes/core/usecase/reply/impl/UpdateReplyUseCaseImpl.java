package com.trepudox.rottenitaumatoes.core.usecase.reply.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.exception.APISecurityException;
import com.trepudox.rottenitaumatoes.core.mapper.ReplyMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reply.IUpdateReplyUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UpdateReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReplyRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateReplyUseCaseImpl implements IUpdateReplyUseCase {

    private final JwtTokenUtil jwtTokenUtil;
    private final ReplyRepository replyRepository;

    @Override
    public ReplyDTO update(String token, UpdateReplyDTO payload) {
        String username = getUsernameFromToken(token);

        ReplyModel replyModel = retrieveReplyModel(payload.getReplyId());

        if(!username.equals(replyModel.getReplier().getUsername()))
            throw new APISecurityException("Acesso negado", "Usuário não possui permissão necessária para acessar esse recurso", 403);

        String text = payload.getText();

        updateReplyModel(replyModel, text);

        return ReplyMapper.INSTANCE.replyModelToReplyDTO(replyModel);
    }

    private String getUsernameFromToken(String token) {
        String jwt = token.replace("Bearer ", "").trim();
        return jwtTokenUtil.getSubjectFromToken(jwt);
    }

    private ReplyModel retrieveReplyModel(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "A reply não existe", 422));
    }

    private void updateReplyModel(ReplyModel replyModel, String text) {
        replyModel.setText(text);
        replyRepository.saveAndFlush(replyModel);
    }

}
