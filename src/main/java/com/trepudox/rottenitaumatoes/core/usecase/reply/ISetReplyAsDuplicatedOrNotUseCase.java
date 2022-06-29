package com.trepudox.rottenitaumatoes.core.usecase.reply;

import com.trepudox.rottenitaumatoes.dataprovider.dto.DuplicatedReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;

public interface ISetReplyAsDuplicatedOrNotUseCase {

    ReplyDTO set(DuplicatedReplyDTO duplicatedReply);

}
