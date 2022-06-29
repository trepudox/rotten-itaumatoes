package com.trepudox.rottenitaumatoes.core.usecase.reply.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReplyMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reply.IGetReplyByIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetReplyByIdUseCaseImpl implements IGetReplyByIdUseCase {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyDTO get(Long replyId) {
        ReplyModel replyModel = replyRepository.findById(replyId)
                .orElseThrow(() -> new APIException("Reply não existe", "Não foi possível encontrar a reply solicitada", 422));
        return ReplyMapper.INSTANCE.replyModelToReplyDTO(replyModel);
    }
}
