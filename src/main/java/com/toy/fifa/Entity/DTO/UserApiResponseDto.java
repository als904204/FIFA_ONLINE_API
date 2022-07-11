package com.toy.fifa.Entity.DTO;

import com.toy.fifa.Entity.User;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserApiResponseDto {
    private String accessId;
    private String nickname;
    private int level;

    // ApiDto -> User Entity
    public User toEntity() {
        return User.builder()
                .accessId(accessId)
                .nickname(nickname)
                .level(level).build();
    }

}
