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
    public User join(String username, String email, String password) {
        duplicatedUserByUsername(username);
        duplicatedUserByEmail(email);
        User joinUser = new User();
        String encodedPassword = passwordEncoder.encode(password);
        joinUser.setUsername(username);
        joinUser.setEmail(email);
        joinUser.setPassword(encodedPassword);
        userRepository.save(joinUser);
        return joinUser;
    }

    // 중복 이름
    private void duplicatedUserByUsername(String name) {
        if (findByUsername(name).isPresent()) {
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

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
