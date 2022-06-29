package com.trepudox.rottenitaumatoes.core.usecase.reply.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReplyMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reply.IGetRepliesByReviewIdUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReplyRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetRepliesByReviewIdUseCaseImpl implements IGetRepliesByReviewIdUseCase {

    private final ReplyRepository replyRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReplyDTO> get(Long repliedReviewId) {
        ReviewModel reviewModel = getReviewModel(repliedReviewId);
        List<ReplyModel> replyModelList = replyRepository.findAllByRepliedReview(reviewModel);

        return ReplyMapper.INSTANCE.replyModelListToReplyDTOList(replyModelList);
    }

    private ReviewModel getReviewModel(Long repliedReviewId) {
        return reviewRepository.findById(repliedReviewId).orElseThrow(
                () -> new APIException("Item não existe", "Não foi possível obter a reply, a review mencionada não existe", 422));
    }
}
