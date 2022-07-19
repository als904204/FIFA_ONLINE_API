package com.toy.fifa.Config;

import com.toy.fifa.Service.Community.CommunityUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {


    private final CommunityUserService userService;



    // 접근 가능
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    // 패스워드 암호화
    @Override
    public void configure(AuthenticationManagerBuilder  auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/**", "/").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/user/auth/login")
                .defaultSuccessUrl("/board/auth/boardList")
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true); // 세션날리기

    }
}
