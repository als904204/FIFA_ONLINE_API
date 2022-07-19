package com.toy.fifa.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


// TODO : 나중에 DTO 로 변경 해야 함
@NoArgsConstructor
@Entity
@Getter
public class User implements UserDetails {

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

    @Builder
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    //사용자의 권한을 콜렉션 형태로 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
