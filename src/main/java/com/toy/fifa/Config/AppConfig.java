package com.toy.fifa.Config;


import com.toy.fifa.DTO.ResponseMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class AppConfig {
    @Bean
    public ResponseMessage responseMessage() {
        return new ResponseMessage();
    }

    @Bean
    public HttpHeaders headers() {
        return new HttpHeaders();
    }
}
