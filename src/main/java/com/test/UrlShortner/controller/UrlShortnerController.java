package com.test.UrlShortner.controller;

import com.test.UrlShortner.model.ErrorResponse;
import com.test.UrlShortner.model.Response;
import com.test.UrlShortner.model.UrlShortenerModel;
import com.test.UrlShortner.service.UrlShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UrlShortnerController {
    @Autowired
    UrlShortenerService urlShortenerService;
    @GetMapping("/url/")
    public String getUrl(@RequestParam String shortenedUrl) {
        log.info("Request URL:{}", shortenedUrl);
        if(urlShortenerService.validateURL(shortenedUrl)) {
            String originalURL = urlShortenerService.retrieveActualURL(shortenedUrl);
            return originalURL;
        }


        return "The shortened url passed :" + shortenedUrl;
    }

    @PostMapping("/shortenURL/")
    public Object createUrl(@RequestBody String url) {
        if(urlShortenerService.validateURL(url)){
            String shortenedURL=urlShortenerService.shortenURL(url);
            return new Response(url,shortenedURL);
        }
        else{
            ErrorResponse errorResponse = urlShortenerService.buildErrorResponseObject(url);
            return errorResponse;
        }
       // return "Generate URL shortener";
    }


    @GetMapping("/stats/")
    public Object getAllURL() {

        List<UrlShortenerModel> responseList=urlShortenerService.getAll();
        // return "Generate URL shortener";
        return responseList;
    }

}