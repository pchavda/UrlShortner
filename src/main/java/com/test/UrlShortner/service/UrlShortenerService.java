package com.test.UrlShortner.service;

import com.test.UrlShortner.model.ErrorResponse;
import com.test.UrlShortner.model.UrlShortenerModel;
import com.test.UrlShortner.repository.UrlShortenerRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {
    @Autowired
    UrlShortenerRepository urlShortenerRepository;
     public boolean validateURL(String url) {

         String[] schemes = {"http","https"};
         UrlValidator urlValidator = new UrlValidator(schemes);
         if (urlValidator.isValid(url)) {
             System.out.println("URL is valid");
             return true;
         } else {
             return false;
         }
    }

    public String shortenURL(String url) {
          String newURL=getAlphaNumericString(10);
         //check if String present in H2
        UrlShortenerModel byShortenedURL = urlShortenerRepository.findByShortURL(url);
        while(byShortenedURL!=null && byShortenedURL.getShortURL()!=newURL) {
             newURL=getAlphaNumericString(10);
             byShortenedURL = urlShortenerRepository.findByShortURL(url);
        }
        UrlShortenerModel obj=new UrlShortenerModel(newURL,url);

        urlShortenerRepository.save(obj);

        return newURL;

    }

    public ErrorResponse buildErrorResponseObject(String url) {
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorMessage("Invalid URL" + url);
        return errorResponse;
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        StringBuilder domainBuilder=new StringBuilder();
        domainBuilder.append("https://urlShortner.com/");
        domainBuilder.append(sb);
        return domainBuilder.toString();
    }


    public String retrieveActualURL(String url) {
      UrlShortenerModel byShortenedURL = urlShortenerRepository.findByShortURL(url);
        if(byShortenedURL==null){
            return "URL not found";
        }

        return byShortenedURL.getOriginalURL();

    }

}

