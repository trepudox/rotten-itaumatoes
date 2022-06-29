package com.trepudox.rottenitaumatoes.core.usecase.reply;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;

import java.util.List;

public interface IGetRepliesByReviewIdUseCase {

    List<ReplyDTO> get(Long repliedReviewId);

}
