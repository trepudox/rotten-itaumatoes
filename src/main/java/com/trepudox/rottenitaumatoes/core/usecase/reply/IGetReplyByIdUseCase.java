package com.trepudox.rottenitaumatoes.core.usecase.reply;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;

public interface IGetReplyByIdUseCase {

    ReplyDTO get(Long replyId);

}
