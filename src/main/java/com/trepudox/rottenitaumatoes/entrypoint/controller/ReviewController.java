package com.trepudox.rottenitaumatoes.entrypoint.controller;

import com.trepudox.rottenitaumatoes.core.usecase.review.*;
import com.trepudox.rottenitaumatoes.dataprovider.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ICreateReviewUseCase createReviewUseCase;
    private final IGetReviewByIdUseCase getReviewByIdUseCase;
    private final IGetReviewsByMovieIdUseCase getReviewsByMovieIdUseCase;
    private final ISetReviewAsDuplicatedOrNotUseCase setReviewAsDuplicatedOrNotUseCase;
    private final IVoteOnReviewUseCase voteOnReviewUseCase;
    private final IUpdateReviewUseCase updateReviewUseCase;
    private final IDeleteReviewByIdUseCase deleteReviewByIdUseCase;

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestHeader("Authorization") String token,
                                                  @Valid @RequestBody CreateReviewDTO payload) {
        ReviewDTO createdReview = createReviewUseCase.create(token, payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @GetMapping("/id/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long reviewId) {
        ReviewDTO review = getReviewByIdUseCase.get(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @GetMapping("/imdb-id/{imdbId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByMovieId(@PathVariable String imdbId) {
        List<ReviewDTO> reviews = getReviewsByMovieIdUseCase.get(imdbId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @PostMapping("/set-duplicated")
    public ResponseEntity<ReviewDTO> setReviewAsDuplicatedOrNot(@Valid @RequestBody DuplicatedReviewDTO duplicatedReview) {
        ReviewDTO review = setReviewAsDuplicatedOrNotUseCase.set(duplicatedReview);
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @PostMapping("/vote")
    public ResponseEntity<ReviewDTO> voteOnReview(@RequestHeader("Authorization") String token,
                                                  @Valid @RequestBody CreateVoteOnReviewDTO payload) {
        ReviewDTO review = voteOnReviewUseCase.vote(token, payload);
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @PatchMapping("/update")
    public ResponseEntity<ReviewDTO> updateReview(@RequestHeader("Authorization") String token,
                                                  @Valid @RequestBody UpdateReviewDTO payload) {
        ReviewDTO updatedReview = updateReviewUseCase.update(token, payload);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReview);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReviewById(@RequestHeader("Authorization") String token,
                                                 @PathVariable Long reviewId) {
        deleteReviewByIdUseCase.delete(token, reviewId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
