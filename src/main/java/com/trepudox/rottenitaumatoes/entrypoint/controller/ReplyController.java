package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.reply.*;
import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.DuplicatedReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.UpdateReplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ICreateReplyUseCase createReplyUseCase;
    private final IGetReplyByIdUseCase getReplyByIdUseCase;
    private final IGetRepliesByReviewIdUseCase getRepliesByReviewIdUseCase;
    private final IGetRepliesByReviewWithQuoteIdUseCase getRepliesByReviewWithQuoteIdUseCase;
    private final ISetReplyAsDuplicatedOrNotUseCase setReplyAsDuplicatedOrNotUseCase;
    private final IUpdateReplyUseCase updateReplyUseCase;
    private final IDeleteReplyByIdUseCase deleteReplyByIdUseCase;

    @PostMapping
    public ResponseEntity<ReplyDTO> createReply(@RequestHeader("Authorization") String token,
                                                @Valid @RequestBody CreateReplyDTO payload) {
        ReplyDTO response = createReplyUseCase.create(token, payload);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/id/{replyId}")
    public ResponseEntity<ReplyDTO> getReplyById(@PathVariable Long replyId) {
        ReplyDTO reply = getReplyByIdUseCase.get(replyId);
        return ResponseEntity.status(HttpStatus.OK).body(reply);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<ReplyDTO>> getRepliesByReviewId(@PathVariable Long reviewId) {
        List<ReplyDTO> replies = getRepliesByReviewIdUseCase.get(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(replies);
    }

    @GetMapping("/review-with-quote/{reviewWithQuoteId}")
    public ResponseEntity<List<ReplyDTO>> getRepliesByReviewWithQuoteId(@PathVariable Long reviewWithQuoteId) {
        List<ReplyDTO> replies = getRepliesByReviewWithQuoteIdUseCase.get(reviewWithQuoteId);
        return ResponseEntity.status(HttpStatus.OK).body(replies);
    }

    @PostMapping("/set-duplicated")
    public ResponseEntity<ReplyDTO> setReplyAsDuplicatedOrNot(@Valid @RequestBody DuplicatedReplyDTO duplicatedReply) {
        ReplyDTO replyDTO = setReplyAsDuplicatedOrNotUseCase.set(duplicatedReply);
        return ResponseEntity.status(HttpStatus.OK).body(replyDTO);
    }

    @PatchMapping("/update")
    public ResponseEntity<ReplyDTO> updateReply(@RequestHeader("Authorization") String token,
                                                @Valid @RequestBody UpdateReplyDTO payload) {
        ReplyDTO updatedReply = updateReplyUseCase.update(token, payload);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReply);
    }

    @DeleteMapping("/delete/{replyId}")
    public ResponseEntity<Void> deleteReplyById(@RequestHeader("Authorization") String token,
                                                @PathVariable Long replyId) {
        deleteReplyByIdUseCase.delete(token, replyId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
