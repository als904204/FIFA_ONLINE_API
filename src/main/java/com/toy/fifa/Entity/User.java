package com.toy.fifa.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String username;

    @Column(nullable = false,length = 255)
    private String password;

    @Column(unique = true)
    private String email;

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }


}
