package com.toy.fifa.Entity.DTO;


import com.toy.fifa.Entity.FIFA_user_info;
import lombok.Data;

@Data
public class UserInfoResponseDto {
    private String accessId;
    private String nickname;
    private int level;

    // Entity -> Dto
    public UserInfoResponseDto(FIFA_user_info entity) {
        this.accessId = entity.getAccessId();
        this.nickname = entity.getNickname();
        this.level = entity.getLevel();
    }

}
