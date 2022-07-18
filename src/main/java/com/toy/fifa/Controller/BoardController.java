package com.toy.fifa.Controller;

import com.toy.fifa.Entity.Board;
import com.toy.fifa.Service.Community.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/auth/boardList")
    public String boardList(Model model) {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "/Board/boardList";
    }

    @GetMapping("/auth/boardDetail/{id}")
    public String boardDetail(Model model, @PathVariable Long id) {
        Board board = boardService.getBoardDetail(id);
        model.addAttribute("boardDetail", board);
        return "/Board/boardDetail";
    }


    @GetMapping("/boardSave")
    public String boardSave() {
        return "/Board/boardSaveForm";
    }

    @DeleteMapping("/boardDelete")
    public String boardDelete() {
        boardService.deleteAllBoards();
        log.warn("모든 게시글 삭제");
        return "redirect:/board/boardList";
    }


}
