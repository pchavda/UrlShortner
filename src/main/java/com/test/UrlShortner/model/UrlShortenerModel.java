package com.test.UrlShortner.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "urlShortener")
public class UrlShortenerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="shortened_url")
    private String shortenedURL;

    @Column(name="original_url")
    private String originalURL;
}
