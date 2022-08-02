package com.toy.fifa.Config.Auth;


import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // /user/loginProc 호출 할 경우 자동으로 loadUserByUsername 실행
    // 내가 만든 User 객체를 UserDetails 의 자식으로 하는 과정
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다");
        });
        return new PrincipalDetails(userEntity);
    }
}
