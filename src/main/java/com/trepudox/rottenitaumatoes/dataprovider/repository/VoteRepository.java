package com.trepudox.rottenitaumatoes.dataprovider.repository;

import com.trepudox.rottenitaumatoes.dataprovider.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteModel, Long> {

    Optional<VoteModel> findByVotingUserAndVotedReview(UserModel votingUser, ReviewModel votedReview);

    Optional<VoteModel> findByVotingUserAndVotedReviewWithQuote(UserModel votingUser, ReviewWithQuoteModel votedReviewWithQuote);

    Optional<VoteModel> findByVotingUserAndVotedReply(UserModel votingUser, ReplyModel votedReply);

}
