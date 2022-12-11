package com.test.UrlShortner.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "urlShortener")
public class UrlShortenerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="short_url")
    private String shortURL;

    @Column(name="original_url")
    private String originalURL;

    public UrlShortenerModel(String small,String big){
        shortURL=small;
        originalURL=big;
    }

   public UrlShortenerModel(){

    }
}
