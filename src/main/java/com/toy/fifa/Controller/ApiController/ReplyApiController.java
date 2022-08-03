package com.toy.fifa.Controller.ApiController;

import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.Reply;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.BoardRepository;
import com.toy.fifa.Repository.UserRepository;
import com.toy.fifa.Service.Community.BoardService;
import com.toy.fifa.Service.Community.ReplyService;
import com.toy.fifa.Service.Community.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplyService replyService;
    private final UserService userService;
    private final BoardService boardService;

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
//    /*  댓글 생성  */
//    @PostMapping("/api/v1/board/{id}/reply")
//    public ResponseEntity replyCreate(@PathVariable Long id, @RequestBody String content, Principal principal) {
//
//
//
//            Board board = boardService.getBoardDetail(id);
//            log.info("게시글 아이디={}" ,board.getId());
//
//            User author = userService.findByUsername(principal.getName());
//            log.info("댓글작성자 username={}",author.getUsername());
//            log.info("댓글 작성자 nickname={}",author.getNickname());
//            log.info("댓글 ID = {}", id);
//            log.info("댓글 내용 = {}", content);
//            Reply createdReply = replyService.createReply(board, content, author);
//
//            return ResponseEntity.ok(createdReply);
//    }

    @PostMapping("/api/v1/board/{id}/reply")
    public ResponseEntity replyCreate(@PathVariable Long id, @RequestBody Reply reply, Principal principal) {
        String author = userService.findByUsername(principal.getName()).getUsername();
        replyService.replySave(author,id,reply);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
