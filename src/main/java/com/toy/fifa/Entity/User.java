package com.toy.fifa.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


// TODO : 나중에 DTO 로 변경 해야 함
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 아이디
    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false,length = 255)
    private String password;

    // email
    @Column(unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public User(Long id, String nickname, String password, String username, RoleType roleType) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.username = username;
        this.roleType = roleType;
    }


}
