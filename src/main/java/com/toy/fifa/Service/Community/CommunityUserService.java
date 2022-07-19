package com.toy.fifa.Service.Community;


import com.toy.fifa.DTO.Community_DTO.UserDto;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommunityUserService implements UserDetailsService {

    private final UserRepository userRepository;



    // TODO : User Entity -> DTO 로 받고 DTO -> Entity 로 변환 후 db 에 save
    @Transactional
    public Long join(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build()).getId();

    }

    public User findById(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 유저를 찾을 수 없습니다");
        });
        return findUser;
    }


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
