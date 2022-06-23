package com.trepudox.rottenitaumatoes.dataprovider.repository;

import com.trepudox.rottenitaumatoes.dataprovider.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
