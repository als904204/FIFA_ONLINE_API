package com.toy.fifa.Service;


import com.toy.fifa.Config.ApiKeyConfig;
import com.toy.fifa.Entity.DTO.UserApiResponseDto;
import lombok.RequiredArgsConstructor;
import javax.inject.Inject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

@RequiredArgsConstructor
@Service
public class UserApiClient {

    private final RestTemplate restTemplate;

    @Inject
    private ApiKeyConfig apiKeyConfig;

    private static final String Authorization ="Authorization";


    public UserApiResponseDto requestUserInfo(String nickname) {
        final String UserInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Authorization, apiKeyConfig.getKey());

        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(UserInfoUrl, HttpMethod.GET, entity, UserApiResponseDto.class, nickname).getBody();
    }

}
