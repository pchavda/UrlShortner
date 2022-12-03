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


    @Column(name="short_url")
    private String shortUrl;

    @Column(name="long_url")
    private String longUrl;
}
