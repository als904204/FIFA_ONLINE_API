package com.toy.fifa.Service;

import com.toy.fifa.Entity.DTO.UserApiResponseDto;
import com.toy.fifa.Entity.DTO.UserInfoResponseDto;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserApiClient userApiClient;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserApiResponseDto requestUserInfo(String nickname) {
        return userApiClient.requestUserInfo(nickname);
    }


    @Transactional
    public String userInfoSave(UserApiResponseDto userApiResponseDto) {
        return userRepository.save(userApiResponseDto.toEntity()).getNickname();
    }


    public UserInfoResponseDto findUserInfoById(String nickname) {
        User entity = userRepository.findById(nickname).orElseThrow(() -> {
             return new IllegalArgumentException("존재하지않는 구단주명입니다");
        });
        return new UserInfoResponseDto(entity);
    }


}
