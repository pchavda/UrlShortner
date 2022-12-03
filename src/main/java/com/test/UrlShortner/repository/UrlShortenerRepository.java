package com.test.UrlShortner.repository;

import com.test.UrlShortner.model.UrlShortenerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlShortenerModel, Long> {
}
