package com.trepudox.rottenitaumatoes.core.usecase.reply;

import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateVoteOnReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;

public interface IVoteOnReplyUseCase {

    ReplyDTO vote(String token, CreateVoteOnReplyDTO payload);

}
