package com.toy.fifa.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@Getter
@Builder
@Entity
public class User {

    @Id
    private String nickname;

    private String accessId;

    private int level;

    private int matchType;

    private int division;

    private String achievementDate;

    public User() {

    }
}
