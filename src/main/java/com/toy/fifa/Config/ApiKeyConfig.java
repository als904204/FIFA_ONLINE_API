package com.toy.fifa.Config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiKeyConfig {
    @Value("${fifa}")
    private String key;

    private static ApiKeyConfig instance = new ApiKeyConfig();

    private ApiKeyConfig() {

    }

    public static ApiKeyConfig getInstance() {
        if (instance == null) {
            return new ApiKeyConfig();
        }
        return instance;
    }
}
