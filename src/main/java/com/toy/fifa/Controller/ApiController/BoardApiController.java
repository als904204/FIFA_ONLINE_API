package com.toy.fifa.Controller.ApiController;


import com.toy.fifa.DTO.ResponseMessage;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.BoardForm;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Service.Community.BoardService;
import com.toy.fifa.Service.Community.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.security.Principal;

@Log4j2
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;
    private final ResponseMessage responseMessage;
    private final HttpHeaders  httpHeaders;
    private final UserService userService;

    // TODO : DTO 변경
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/v1/board/create")
    public ResponseEntity<ResponseMessage> boardSave(@RequestBody Board board, Principal principal) {

        User author = userService.findByUsername(principal.getName());

        Board createdBoard = boardService.createBoard(board,author);
        responseMessage.setData(createdBoard);
        return getResponseMessageResponseEntity(HttpStatus.OK.value(), "게시글이 성공적으로 작성되었습니다", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/api/v1/board/{id}")
    public ResponseEntity<ResponseMessage> boardUpdate(@PathVariable Long id,
                                                       @RequestBody Board board,
                                                        Principal username,
                                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return getResponseMessageResponseEntity(HttpStatus.BAD_REQUEST.value(), "bindingResult 오류 : " + bindingResult, HttpStatus.BAD_REQUEST);
        }
        log.info("Principal={}",username);
        log.info("Principal.getName()={}",username.getName());

        Board updatedBoard = boardService.updateBoard(id, board,username);
        responseMessage.setData(updatedBoard);
        return getResponseMessageResponseEntity(HttpStatus.OK.value(), "게시글이 성공적으로 수정되었습니다", HttpStatus.OK);

    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/api/v1/board/{id}")
    public ResponseEntity<ResponseMessage> boardDelete(@PathVariable Long id) {

        // TODO : 현재 사용자와 게시글 작성자 비교 해야 함
        log.info("삭제할ID={}",id);
        boardService.deleteBoard(id);
        return getResponseMessageResponseEntity(HttpStatus.OK.value(), "게시글이 성공적으로 삭제되었습니다", HttpStatus.OK);

    }

    // TODO : 메서드 공통
    private ResponseEntity<ResponseMessage> getResponseMessageResponseEntity(int httpsStatus, String HttpMessage, HttpStatus httpResponseStatus) {

        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        responseMessage.setStatus(httpsStatus);
        responseMessage.setMessage(HttpMessage);
        return new ResponseEntity<>(responseMessage, httpHeaders, httpResponseStatus);
    }
}
