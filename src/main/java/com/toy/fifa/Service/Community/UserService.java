package com.toy.fifa.Service.Community;


import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // TODO : User Entity -> DTO 로 받고 DTO -> Entity 로 변환 후 db 에 save
    public User join(User user) {
        User joinUser = new User();
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return user;
    }

    public User findById(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 유저를 찾을 수 없습니다");
        });
        return findUser;
    }


}
