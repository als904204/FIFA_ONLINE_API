package com.toy.fifa.DTO.FIFA_DTO;

import com.toy.fifa.Entity.FIFA_user_info;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserApiResponseDto {
    private String accessId;
    private String nickname;
    private int level;

    // ApiDto -> User Entity
    public FIFA_user_info toEntity() {
        return FIFA_user_info.builder()
                .accessId(accessId)
                .nickname(nickname)
                .level(level).build();
    }

}
