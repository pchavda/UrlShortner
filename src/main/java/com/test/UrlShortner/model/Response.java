package com.test.UrlShortner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

    String inputURL;
    String outputURL;
}
