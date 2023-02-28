package com.src.application.urlshortener.service;

import com.src.application.urlshortener.model.ErrorResponse;
import com.src.application.urlshortener.model.UrlShortenerModel;
import com.src.application.urlshortener.repository.UrlShortenerRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.List;

@Service
@Slf4j
public class UrlShortenerService extends CommonsRequestLoggingFilter {
    @Autowired
    UrlShortenerRepository urlShortenerRepository;

    @Value("${application.url.shortener.length:10}")
    public int size;

    public static final String domainName="https://urlShortner.com/";
     public boolean validateURL(String url) {
           log.info("Original URL {}",url);
         String[] schemes = {"http","https"};
         UrlValidator urlValidator = new UrlValidator(schemes);
         if (urlValidator.isValid(url)) {
             log.debug("URL is valid {}", url);
             return true;
         } else {
             return false;
         }
    }

    public String shortenURL(String url) {
        String newURL=getAlphaNumericString(size);
        //check if String present in H2
        UrlShortenerModel byShortenedURL = urlShortenerRepository.findByShortURL(url);


        // Testing paginated calls
        testPaginatedCalls(url);

        while(byShortenedURL!=null && !byShortenedURL.getShortURL().equals(newURL)) {
             newURL=getAlphaNumericString(10);
             byShortenedURL = urlShortenerRepository.findByShortURL(url);
        }

        UrlShortenerModel obj=new UrlShortenerModel(newURL,url);

        urlShortenerRepository.save(obj);

        return newURL;

    }

    private void testPaginatedCalls(String url) {
        PageRequest pageable=PageRequest.of(1,1, Sort.unsorted());
        Page<UrlShortenerModel> allUsersWithPagination = urlShortenerRepository.findAllShortUrlsWithPagination(pageable);

        allUsersWithPagination.getTotalPages();
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
         ;
        return  domainName + sb;
    }


    public String retrieveActualURL(String url) {
      UrlShortenerModel byShortenedURL = urlShortenerRepository.findByShortURL(url);
        if(byShortenedURL==null){
            return "URL not found";
        }

        return byShortenedURL.getOriginalURL();

    }

    public List<UrlShortenerModel> getAll() {

       return urlShortenerRepository.findAll();
    }
}

