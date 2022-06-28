package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.reviewWithQuote.*;
import com.trepudox.rottenitaumatoes.dataprovider.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reviews-with-quote")
@RequiredArgsConstructor
public class ReviewWithQuoteController {

    private final ICreateReviewWithQuoteUseCase createReviewWithQuoteUseCase;
    private final IGetReviewWithQuoteByIdUseCase getReviewWithQuoteByIdUseCase;
    private final IGetReviewsWithQuoteByMovieIdUseCase getReviewsWithQuoteByMovieIdUseCase;
    private final ISetReviewWithQuoteAsDuplicatedOrNotUseCase setReviewWithQuoteAsDuplicatedOrNotUseCase;
    private final IUpdateReviewWithQuoteUseCase updateReviewWithQuoteUseCase;
    private final IDeleteReviewWithQuoteByIdUseCase deleteReviewWithQuoteByIdUseCase;

    @PostMapping
    public ResponseEntity<ReviewWithQuoteDTO> createReviewWithQuote(@RequestHeader("Authorization") String token,
                                                                    @Valid @RequestBody CreateReviewWithQuoteDTO payload) {
        ReviewWithQuoteDTO createdReviewWithQuote = createReviewWithQuoteUseCase.create(token, payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReviewWithQuote);
    }

    @GetMapping("/id/{reviewWithQuoteId}")
    public ResponseEntity<ReviewWithQuoteDTO> getReviewWithQuoteById(@PathVariable Long reviewWithQuoteId) {
        ReviewWithQuoteDTO reviewWithQuote = getReviewWithQuoteByIdUseCase.get(reviewWithQuoteId);
        return ResponseEntity.status(HttpStatus.OK).body(reviewWithQuote);
    }

    @GetMapping("/imdb-id/{imdbId}")
    public ResponseEntity<List<ReviewWithQuoteDTO>> getReviewsWithQuoteByMovieId(@PathVariable String imdbId) {
        List<ReviewWithQuoteDTO> reviewsWithQuote = getReviewsWithQuoteByMovieIdUseCase.get(imdbId);
        return ResponseEntity.status(HttpStatus.OK).body(reviewsWithQuote);
    }

    @PostMapping("/set-duplicated")
    public ResponseEntity<ReviewWithQuoteDTO> setReviewWithQuoteAsDuplicatedOrNot(@Valid @RequestBody DuplicatedReviewDTO duplicatedReviewWithQuote) {
        ReviewWithQuoteDTO reviewWithQuote = setReviewWithQuoteAsDuplicatedOrNotUseCase.set(duplicatedReviewWithQuote);
        return ResponseEntity.status(HttpStatus.OK).body(reviewWithQuote);
    }

    @PatchMapping("/update")
    public ResponseEntity<ReviewWithQuoteDTO> updateReviewWithQuote(@RequestHeader("Authorization") String token,
                                                           @Valid @RequestBody UpdateReviewDTO payload) {
        ReviewWithQuoteDTO updatedReviewWithQuote = updateReviewWithQuoteUseCase.update(token, payload);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReviewWithQuote);
    }

    @DeleteMapping("/delete/{reviewWithQuoteId}")
    public ResponseEntity<Void> deleteReviewWithQuoteById(@RequestHeader("Authorization") String token,
                                                          @PathVariable Long reviewWithQuoteId) {
        deleteReviewWithQuoteByIdUseCase.delete(token, reviewWithQuoteId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
