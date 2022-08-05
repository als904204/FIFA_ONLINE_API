package com.toy.fifa.Service.Community;

import com.toy.fifa.Config.ExceptionConfig.DataNotFoundException;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * The type Board service.
 */
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * Gets board list.
     *
     * @param pageable the pageable
     * @return the board list
     */
// 모든 게시글 조회
    public Page<Board> getBoardList(Pageable pageable) {
        Page<Board> boardList = boardRepository.findAll(pageable);
        return boardList;
    }

    /**
     * Gets board detail.
     *
     * @param id the id
     * @return the board detail
     */
// 게시글 자세히 보기
    public Board getBoardDetail(Long id) {
       Board board =  boardRepository.findById(id).orElseThrow(()-> {
           return new DataNotFoundException("해당 게시글을 찾을 수 없습니다 : " + id);
       });
       return board;
    }

    /**
     * Create board board.
     *
     * @param boardDTO the board dto
     * @param author   the author
     * @return the board
     */
// 게시글 작성
    // TODO : DTO 변경 > 작성자값만 가져와야 되는데 유저 모든 값 가져옴
    public Board createBoard(Board boardDTO, User author) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setCreateDate(LocalDateTime.now());
        board.setAuthor(author);
        boardRepository.save(board);
        return board;
    }


    /**
     *
     * @param id
     * @return
     */
    public Board findByBoardId(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("게시글을 찾을 수 없습니다");
        });
        return board;
    }


    // 작성된 게시글 username 과 현재 로그인한 유저 세션 username 비교
    public boolean isPrincipalUser(Principal principal, Board board) {
        if (!board.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        } else
            return true;
    }

    // 게시글 삭제
    public void deleteAllBoards(Long id) {
        boardRepository.deleteById(id);
    }

}
