package com.src.application.urlshortener.repository;

import com.src.application.urlshortener.model.UrlShortenerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlShortenerModel, Long> {

    UrlShortenerModel findByShortURL(String shortenedURL);

    @Query(
            value = "SELECT * FROM urlShortener u WHERE u.short_url = :shortenedURL",
            nativeQuery = true)
    Collection<UrlShortenerModel> findAllActiveUsersNative( @Param("status") String shortenedURL);

    Page<UrlShortenerModel> findAllUsersWithPagination(Pageable pageable);
}
