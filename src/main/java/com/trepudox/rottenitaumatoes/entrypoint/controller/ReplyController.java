package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.reply.ICreateReplyUseCase;
import com.trepudox.rottenitaumatoes.dataprovider.dto.CreateReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ICreateReplyUseCase createReplyUseCase;

    @PostMapping
    public ResponseEntity<ReplyDTO> createReply(@RequestHeader("Authorization") String token,
                                                @Valid @RequestBody CreateReplyDTO payload) {
        ReplyDTO response = createReplyUseCase.create(token, payload);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
