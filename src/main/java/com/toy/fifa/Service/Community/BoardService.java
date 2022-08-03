package com.toy.fifa.Service.Community;

import com.toy.fifa.Config.ExceptionConfig.DataNotFoundException;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 모든 게시글 조회
    public Page<Board> getBoardList(Pageable pageable) {
        Page<Board> boardList = boardRepository.findAll(pageable);
        return boardList;
    }

    // 게시글 자세히 보기
    public Board getBoardDetail(Long id) {
       Board board =  boardRepository.findById(id).orElseThrow(()-> {
           return new DataNotFoundException("해당 게시글을 찾을 수 없습니다 : " + id);
       });
       return board;
    }

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

    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("게시글을 찾을 수 없습니다");
        });
        return board;
    }

    // 게시글 삭제
    public void deleteAllBoards(Long id) {
        boardRepository.deleteById(id);
    }

}
