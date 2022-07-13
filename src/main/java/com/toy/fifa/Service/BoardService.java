package com.toy.fifa.Service;

import com.toy.fifa.Entity.Board;
import com.toy.fifa.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

}
