package com.trepudox.rottenitaumatoes.dataprovider.repository;

import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuote;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuotePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewWithQuoteRepository extends JpaRepository<ReviewWithQuote, ReviewWithQuotePK> {
}
