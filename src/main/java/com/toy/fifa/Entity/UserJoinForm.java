package com.toy.fifa.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserJoinForm {

    @Size(min = 6, max = 25)
    @NotEmpty(message = "아이디를 입력해주세요")
    private String username;

    @Size(min = 8)
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @Size(min = 8)
    @NotEmpty(message = "비밀번호 확인칸을 입력해주세요.")
    private String confirmPassword;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;


}
