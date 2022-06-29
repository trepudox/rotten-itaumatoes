package com.trepudox.rottenitaumatoes.dataprovider.repository;

import com.trepudox.rottenitaumatoes.dataprovider.model.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteModel, Long> {

    Optional<VoteModel> findByVotingUserAndVotedReview(String username, Long reviewId);

    Optional<VoteModel> findByVotingUserAndVotedReviewWithQuote(String username, Long reviewWithQuoteId);

    Optional<VoteModel> findByVotingUserAndVotedReply(String username, Long replyId);

}
