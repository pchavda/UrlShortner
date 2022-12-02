package com.test.UrlShortner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortnerController {

    @GetMapping("/url")
    public String getUrl(){
        return "This is the test application to get the url";
    }
    @PostMapping("/url/")
    public String createUrl(@RequestBody String url){
        return "Generate url shortner";
    }
}
