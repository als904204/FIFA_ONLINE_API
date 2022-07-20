package com.toy.fifa.DTO.Board_DTO;


import com.toy.fifa.Entity.Reply;
import com.toy.fifa.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class boardDto {
    private String title;
    private String content;
    private LocalDateTime createDate;
    private int count;
    private List<Reply> replyList;
    private User author;
}
