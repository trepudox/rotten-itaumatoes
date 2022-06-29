package com.trepudox.rottenitaumatoes.dataprovider.repository;

import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewModel;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyModel, Long> {

    List<ReplyModel> findAllByRepliedReview(ReviewModel repliedReview);

    List<ReplyModel> findAllByRepliedReviewWithQuote(ReviewWithQuoteModel repliedReviewWithQuote);

}
