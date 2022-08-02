package com.toy.fifa.Config.Auth;

import com.toy.fifa.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 유저가 입력한 id,pw 를 시큐리티가 낚아채서 로그인 진행
// 로그인 진행이 완료가 되면 시큐리티 Session(키) 을 만듦 (Security ContextHolder)
// Session 의 객체 자료형은 Authentication 객체임
// Authentication 안에는 User 정보가 있어야 됨
// User 오브젝트 타입 => UserDetails 타입 객체

// 즉 Security Session => Authentication => UserDetails => 내가만든 유저 상속

public class PrincipalDetails implements UserDetails {


    private final User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_" + user.getRoleType(); // ROLE_USER , ROLE_ADMIN
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
