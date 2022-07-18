package com.toy.fifa.Controller.ApiController;


import com.toy.fifa.DTO.Board_DTO.BoardResponseDto;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Service.Community.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/board/auth/boardSave")
    public String boardSave(@RequestParam String title, @RequestParam String content) {
        boardService.createBoard(title,content);
        return "redirect:/board/auth/boardList";
    }
}
