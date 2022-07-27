package com.toy.fifa.Controller.ApiController;

import com.toy.fifa.DTO.ResponseMessage;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.Reply;
import com.toy.fifa.Service.Community.BoardService;
import com.toy.fifa.Service.Community.ReplyService;
import com.toy.fifa.Service.Community.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@Log4j2
@RequiredArgsConstructor
@RestController
public class ReplyApiController {


    private final BoardService boardService;
    private final ReplyService replyService;
    private final ResponseMessage responseMessage;
    private final HttpHeaders  httpHeaders;

    @PostMapping("/api/v1/reply/create/{id}")
    public ResponseEntity<ResponseMessage> replyCrate(@PathVariable Long id, @RequestBody Reply reply) {
        log.info("댓글메서드");
        log.info("해당 댓글 게시글 번호  = {}",id);
        log.info("해당 댓글 내용={}",reply.getContent());

        Board board = boardService.getBoard(id);
        replyService.createReply(board, reply);

        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        responseMessage.setStatus(HttpStatus.OK.value());
        responseMessage.setMessage("성공 코드");
        responseMessage.setData(reply);

        return new ResponseEntity<>(responseMessage, httpHeaders, HttpStatus.OK);
    }

}
