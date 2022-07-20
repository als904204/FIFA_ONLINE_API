package com.toy.fifa.Controller;

import com.toy.fifa.DTO.ResponseUserDto;
import com.toy.fifa.Entity.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class ReplyController {

    @PostMapping("/api/v1/reply/create/{id}")
    public ResponseUserDto<Integer> replyCrate(@PathVariable Long id, @RequestBody String content) {
        log.info("댓글메서드");
        log.info("해당 댓글 게시글 번호  = {}",id);
        log.info("해당 댓글 내용={}",content);
        return new ResponseUserDto<>(HttpStatus.OK.value(), 1);
    }

}
