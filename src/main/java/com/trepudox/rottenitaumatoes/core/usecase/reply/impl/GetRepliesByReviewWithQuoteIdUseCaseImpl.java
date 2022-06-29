package com.trepudox.rottenitaumatoes.core.usecase.reply.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReplyMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reply.IGetRepliesByReviewWithQuoteIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReplyRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetRepliesByReviewWithQuoteIdUseCaseImpl implements IGetRepliesByReviewWithQuoteIdUseCase {

    private final ReplyRepository replyRepository;
    private final ReviewWithQuoteRepository reviewWithQuoteRepository;

    @Override
    public List<ReplyDTO> get(Long repliedReviewWithQuoteId) {
        ReviewWithQuoteModel reviewWithQuoteModel = getReviewWithQuoteModel(repliedReviewWithQuoteId);
        List<ReplyModel> replyModelList = replyRepository.findAllByRepliedReviewWithQuote(reviewWithQuoteModel);

        return ReplyMapper.INSTANCE.replyModelListToReplyDTOList(replyModelList);
    }

    private ReviewWithQuoteModel getReviewWithQuoteModel(Long quotedReviewId) {
        return reviewWithQuoteRepository.findById(quotedReviewId).orElseThrow(
                () -> new APIException("Item não existe", "Não foi possível obter a reply, a review with quote mencionada não existe", 422));
    }
}
