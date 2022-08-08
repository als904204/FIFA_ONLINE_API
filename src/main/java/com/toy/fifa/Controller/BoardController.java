package com.toy.fifa.Controller;

import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.BoardForm;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Service.Community.BoardService;
import com.toy.fifa.Service.Community.ReplyService;
import com.toy.fifa.Service.Community.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final UserService userService;

    @RequestMapping("/boardList")
    public String boardList(Model model, @PageableDefault(page = 0, size = 5,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, Principal principal) {
        Page<Board> boardList = boardService.getBoardList(pageable);

        int currentPage = boardList.getPageable().getPageNumber() + 1; // 현재 페이지
        int startPage = Math.max(currentPage - 4,1); // 현제페이지가 1일 경우 -3 이 나오는데 -3을 1과 비교 하여 더 큰값 반환
        int endPage = Math.min(currentPage + 5,boardList.getTotalPages());

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/Board/boardList";
    }

    // 조회
    @GetMapping("/boardDetail/{id}")
    public String boardDetail(Model model, @PathVariable Long id) {

        Board board = boardService.getBoardDetail(id);
        model.addAttribute("boardDetail", board);
        return "/Board/boardDetail";
    }

    // 저장
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/boardSave")
    public String boardSave() {
        return "/Board/boardSaveForm";
    }

    // 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/boardUpdateForm")
    public String boardUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("board",boardService.getBoardDetail(id));
        return "/Board/boardUpdateForm";
    }




    // 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}/boardDelete")
    public String boardDelete(@PathVariable Long id) {
        log.warn("게시글 삭제");
        return "";
    }


}
