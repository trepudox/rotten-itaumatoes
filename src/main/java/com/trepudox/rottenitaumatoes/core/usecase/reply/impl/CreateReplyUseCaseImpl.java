package com.trepudox.rottenitaumatoes.core.usecase.reply.impl;

import com.trepudox.rottenitaumatoes.core.exception.APIException;
import com.trepudox.rottenitaumatoes.core.mapper.ReplyMapper;
import com.trepudox.rottenitaumatoes.core.usecase.reply.ICreateReplyUseCase;
import com.trepudox.rottenitaumatoes.core.usecase.user.IUpdateScoreAndProfileUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.enums.EnReviewType;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.UserModel;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReplyRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.ReviewWithQuoteRepository;
import com.trepudox.rottenitaumatoes.dataprovider.repository.UserRepository;
import com.trepudox.rottenitaumatoes.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CreateReplyUseCaseImpl implements ICreateReplyUseCase {

    private final JwtTokenUtil jwtTokenUtil;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewWithQuoteRepository reviewWithQuoteRepository;
    private final IUpdateScoreAndProfileUseCase updateScoreAndProfileUseCase;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReplyDTO create(String token, CreateReplyDTO payload) {
        String username = getUsernameFromToken(token);
        UserModel userModel = getUserModel(username);

        Long reviewId = payload.getReviewId();

        ReviewModel reviewModel = null;
        ReviewWithQuoteModel reviewWithQuoteModel = null;
        if(payload.getReviewType().toUpperCase().equals(EnReviewType.REVIEW_WITH_QUOTE.name())) {
            reviewWithQuoteModel = getReviewWithQuoteModel(reviewId);
        } else if(payload.getReviewType().toUpperCase().equals(EnReviewType.NORMAL_REVIEW.name())) {
            reviewModel = getReviewModel(reviewId);
        } else {
            throw new APIException("Parâmetro inválido", "O parâmetro 'reviewType' precisa ser igual a 'NORMAL_REVIEW' ou 'REVIEW_WITH_QUOTE'", 400);
        }

        ReplyModel replyModel = createReplyModel(reviewModel, reviewWithQuoteModel, userModel, payload.getText());
        replyRepository.save(replyModel);

        updateScoreAndProfileUseCase.update(username);

        return ReplyMapper.INSTANCE.replyModelToReplyDTO(replyModel);
    }

    private String getUsernameFromToken(String token) {
        String jwt = token.replace("Bearer ", "").trim();
        return jwtTokenUtil.getSubjectFromToken(jwt);
    }

    private UserModel getUserModel(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new APIException("Solicitação não atendida", "O usuario da requisição não existe", 500));
    }

    private ReviewModel getReviewModel(Long normalReviewId) {
        return reviewRepository.findById(normalReviewId).orElseThrow(
                () -> new APIException("Item não existe", "Não foi possível criar a reply, a review mencionada não existe", 422));
    }

    private ReviewWithQuoteModel getReviewWithQuoteModel(Long reviewWithQuoteId) {
        return reviewWithQuoteRepository.findById(reviewWithQuoteId).orElseThrow(
                () -> new APIException("Item não existe", "Não foi possível criar a reply, a review with quote mencionada não existe", 422));
    }

    private ReplyModel createReplyModel(ReviewModel reviewModel, ReviewWithQuoteModel reviewWithQuoteModel, UserModel replier, String text) {
        return ReplyModel.builder()
                .repliedReview(reviewModel)
                .repliedReviewWithQuote(reviewWithQuoteModel)
                .replier(replier)
                .text(text)
                .likes(0L).dislikes(0L).duplicated(false)
                .creationDateTime(LocalDateTime.now())
                .build();
    }

}
