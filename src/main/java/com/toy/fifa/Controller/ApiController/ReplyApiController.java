package com.toy.fifa.Controller.ApiController;

import com.toy.fifa.DTO.ResponseMessage;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/v1/board/{id}/reply")
    public ResponseEntity replyCreate(@PathVariable Long id, @RequestBody Reply reply, Principal principal) {
        String author = userService.findByUsername(principal.getName()).getUsername();
        replyService.replySave(author,id,reply);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/api/v1/board/{id}/reply")
    public ResponseEntity replyDelete(@PathVariable Long id) {

        // TODO : 현재 사용자와 게시글 작성자 비교 해야 함
        log.info("삭제할ID={}",id);
        replyService.replyDelete(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }


}
