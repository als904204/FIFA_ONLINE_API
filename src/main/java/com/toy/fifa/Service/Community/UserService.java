package com.toy.fifa.Service.Community;


import com.toy.fifa.Config.ExceptionConfig.DuplicatedEmailException;
import com.toy.fifa.Config.ExceptionConfig.DuplicatedUserException;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // TODO : User Entity -> DTO 로 받고 DTO -> Entity 로 변환 후 db 에 save
    @Transactional
    public User join(String nickname, String email, String password) {
        duplicatedUserByNickname(nickname);
        duplicatedUserByEmail(email);
        User joinUser = new User();
        String encodedPassword = passwordEncoder.encode(password);
        joinUser.setNickname(nickname);
        joinUser.setEmail(email);
        joinUser.setPassword(encodedPassword);
        userRepository.save(joinUser);
        return joinUser;
    }

    // 중복 닉네임
    private void duplicatedUserByNickname(String nickname) {
        if (findByNickname(nickname).isPresent()) {
            throw new DuplicatedUserException();
        }
    }

    // 중복 메일
    private void duplicatedUserByEmail(String email) {
        if (findByEmail(email).isPresent()) {
            throw new DuplicatedEmailException();
        }
    }


    public User findById(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 유저를 찾을 수 없습니다");
        });
        return findUser;
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
