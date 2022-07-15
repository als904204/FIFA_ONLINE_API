package com.toy.fifa.Controller;

import com.toy.fifa.Entity.Board;
import com.toy.fifa.Service.Community.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @RequestMapping("/boardList")
    public String boardList(Model model) {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "/Board/boardList";
    }

    @GetMapping("/boardDetail/{id}")
    public String boardDetail(Model model, @PathVariable Long id) {
        Board board = boardService.getBoardDetail(id);
        model.addAttribute("boardDetail", board);
        return "/Board/boardDetail";
    }


    @GetMapping("/boardSave")
    public String boardSave() {
        return "/Board/boardSaveForm";
    }


}
