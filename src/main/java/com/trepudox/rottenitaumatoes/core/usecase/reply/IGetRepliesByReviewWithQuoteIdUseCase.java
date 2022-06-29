package com.trepudox.rottenitaumatoes.core.usecase.reply;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;

import java.util.List;

public interface IGetRepliesByReviewWithQuoteIdUseCase {

    List<ReplyDTO> get(Long repliedReviewWithQuoteId);

}
