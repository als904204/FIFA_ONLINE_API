package com.toy.fifa.Controller.ApiController;


import com.toy.fifa.DTO.ResponseMessage;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Service.Community.BoardService;
import com.toy.fifa.Service.Community.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@Log4j2
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;
    private final ResponseMessage responseMessage;
    private final HttpHeaders  httpHeaders;

    // TODO : DTO 변경
    @PostMapping("/api/v1/board/create")
    public ResponseEntity<ResponseMessage> boardSave(@RequestBody Board board) {
        boardService.createBoard(board);


        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        responseMessage.setStatus(HttpStatus.OK.value());
        responseMessage.setMessage("성공 코드");
        responseMessage.setData(board);

        return new ResponseEntity<>(responseMessage, httpHeaders, HttpStatus.OK);
    }
}
