package com.test.UrlShortner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class UrlShortnerController {

    @GetMapping("/url")
    public String getUrl(@RequestParam String shortendUrl){
        log.info("Request URL:{}",shortendUrl);
        return "The shortend url passed :"+shortendUrl;
    }
    @PostMapping("/url/")
    public String createUrl(@RequestBody String url){
        return "Generate url shortner";
    }
}
