package com.trepudox.rottenitaumatoes.dataprovider.repository;

import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewWithQuoteRepository extends JpaRepository<ReviewWithQuoteModel, Long> {

    List<ReviewWithQuoteModel> findAllByImdbId(String imdbId);

}
