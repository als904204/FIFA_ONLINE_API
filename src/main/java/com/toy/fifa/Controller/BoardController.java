package com.toy.fifa.Controller;

import com.toy.fifa.Entity.Board;
import com.toy.fifa.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @RequestMapping("/board/boardList")
    public String boardList(Model model) {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "/Board/boardList";
    }
}
