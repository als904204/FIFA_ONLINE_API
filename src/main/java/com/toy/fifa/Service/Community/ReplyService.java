package com.toy.fifa.Service.Community;


import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.Reply;
import com.toy.fifa.Repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReplyService {


    private final ReplyRepository replyRepository;

    // TODO : DTO 로 변경, 작성자 추가
    public void createReply(Board board, Reply replyDto) {
        Reply reply = new Reply();
        reply.setBoard(board);
        reply.setContent(replyDto.getContent());
        reply.setCreateDate(LocalDateTime.now());

        replyRepository.save(reply);
    }
}
