package com.toy.fifa.Controller.ApiController;


import com.toy.fifa.DTO.Board_DTO.BoardResponseDto;
import com.toy.fifa.DTO.ResponseUserDto;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Service.Community.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    // TODO : DTO 변경
    @PostMapping("/api/v1/board/create")
    public ResponseUserDto<Integer> boardSave(@RequestBody Board board) {
        boardService.createBoard(board);
        return new ResponseUserDto<>(HttpStatus.OK.value(), 1);
    }
}
