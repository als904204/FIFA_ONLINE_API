package com.toy.fifa.Service.Community;

import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    void 유저회원가입() {

        //given
        User user = new User(1L, "user1", "pwd", "naver@naver");

        //when
        User u1 = userService.join(user);

        //then
        Assertions.assertThat(u1.getId()).isEqualTo(1L);
    }

}