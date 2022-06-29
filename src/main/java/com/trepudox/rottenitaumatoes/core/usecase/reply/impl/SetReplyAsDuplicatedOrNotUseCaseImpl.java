package com.trepudox.rottenitaumatoes.core.usecase.reply.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReplyMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reply.ISetReplyAsDuplicatedOrNotUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.DuplicatedReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetReplyAsDuplicatedOrNotUseCaseImpl implements ISetReplyAsDuplicatedOrNotUseCase {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyDTO set(DuplicatedReplyDTO duplicatedReply) {
        ReplyModel replyModel = retrieveReplyModel(duplicatedReply.getReplyId());
        Boolean boolToSet = duplicatedReply.getDuplicated();

        checkSentParameter(replyModel, boolToSet);

        replyModel.setDuplicated(boolToSet);
        replyRepository.saveAndFlush(replyModel);

        return ReplyMapper.INSTANCE.replyModelToReplyDTO(replyModel);
    }

    private ReplyModel retrieveReplyModel(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "A reply não existe", 422));
    }

    private void checkSentParameter(ReplyModel replyModel, Boolean boolToSet) {
        if(replyModel.getDuplicated().equals(boolToSet))
            throw new APIException("Solicitação não atendida", String.format("Campo 'duplicated' já está marcado como '%s'", boolToSet), 422);
    }

}
