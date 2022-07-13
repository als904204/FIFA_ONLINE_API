package com.toy.fifa.Service.FIFA;

import com.toy.fifa.Entity.DTO.UserApiResponseDto;
import com.toy.fifa.Entity.DTO.UserInfoResponseDto;
import com.toy.fifa.Entity.FIFA_user_info;
import com.toy.fifa.Repository.FIFA_api_UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FIFA_api_UserService {

    private final FIFA_UserApiClient userApiClient;
    private final FIFA_api_UserRepository FIFAapiUserRepository;

    @Transactional(readOnly = true)
    public UserApiResponseDto requestUserInfo(String nickname) {
        return userApiClient.requestUserInfo(nickname);
    }


    @Transactional
    public String userInfoSave(UserApiResponseDto userApiResponseDto) {
        return FIFAapiUserRepository.save(userApiResponseDto.toEntity()).getNickname();
    }


    public UserInfoResponseDto findUserInfoById(String nickname) {
        FIFA_user_info entity = FIFAapiUserRepository.findById(nickname).orElseThrow(() -> {
             return new IllegalArgumentException("존재하지않는 구단주명입니다");
        });
        return new UserInfoResponseDto(entity);
    }


}
