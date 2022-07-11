package com.toy.fifa.Entity.DTO;


import com.toy.fifa.Entity.User;
import lombok.Data;

@Data
public class UserInfoResponseDto {
    private String accessId;
    private String nickname;
    private int level;

    // Entity -> Dto
    public UserInfoResponseDto(User entity) {
        this.accessId = entity.getAccessId();
        this.nickname = entity.getNickname();
        this.level = entity.getLevel();
    }

}
