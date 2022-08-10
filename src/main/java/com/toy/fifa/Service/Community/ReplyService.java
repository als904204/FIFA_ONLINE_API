package com.toy.fifa.Service.Community;

import com.toy.fifa.Config.ExceptionConfig.DataNotFoundException;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.Reply;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.BoardRepository;
import com.toy.fifa.Repository.ReplyRepository;
import com.toy.fifa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void replySave(String username, Long boardId, Reply  reply) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new DataNotFoundException("해당 유저를 찾을 수 없습니다 : " + username)
        );
        Board board = boardRepository.findById(boardId).orElseThrow(()->
            new IllegalArgumentException("해당 게시글을 찾을 수 없습니다 : " + boardId)
        );

        Reply replyEntity = new Reply();
        replyEntity.setAuthor(user);
        replyEntity.setBoard(board);
        replyEntity.setContent(reply.getContent());
        replyEntity.setCreateDate(LocalDateTime.now());

        replyRepository.save(replyEntity);
    }

    @Transactional
    public void replyDelete(Long id) {
        replyRepository.deleteById(id);
    }

}
