package com.trepudox.rottenitaumatoes.core.usecase.reply;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UpdateReplyDTO;

public interface IUpdateReplyUseCase {

    ReplyDTO update(String token, UpdateReplyDTO payload);

}
