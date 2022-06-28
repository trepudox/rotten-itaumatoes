package com.trepudox.rottenitaumatoes.core.usecase.reply;

import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;

public interface ICreateReplyUseCase {

    ReplyDTO create(String token, CreateReplyDTO payload);

}
