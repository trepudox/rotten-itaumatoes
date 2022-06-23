package com.trepudox.rottenitaumatoes.dataprovider.repository;

import com.trepudox.rottenitaumatoes.dataprovider.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}