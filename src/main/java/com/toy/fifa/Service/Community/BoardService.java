package com.toy.fifa.Service.Community;

import com.toy.fifa.Config.ExceptionConfig.DataNotFoundException;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.Reply;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.BoardRepository;
import com.toy.fifa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;


@Log4j2
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    // 모든 게시글 조회
    @Transactional(readOnly = true)
    public Page<Board> getBoardList(Pageable pageable) {
        Page<Board> boardList = boardRepository.findAll(pageable);
        return boardList;
    }


    // 게시글 자세히 보기
    @Transactional(readOnly = true)
    public Board getBoardDetail(Long id) {
       Board board =  boardRepository.findById(id).orElseThrow(()-> {
           return new DataNotFoundException("해당 게시글을 찾을 수 없습니다 : " + id);
       });

       return board;
    }


    // 게시글 작성
    // TODO : DTO 변경 > 작성자값만 가져와야 되는데 유저 모든 값 가져옴
    @Transactional
    public Board createBoard(Board boardDTO, User author) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setCreateDate(LocalDateTime.now());
        board.setAuthor(author);
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public Board updateBoard(Long id, Board requestBoard,Principal principal) {
        // 영속화
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("게시글을 찾을 수 없습니다 해당 ID : " + id);
        });

        // 현재 로그인한 유저 세션 == 작성된 게시글 유저 비교
        isPrincipalUser(principal, board);

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        board.setModifyDate(LocalDateTime.now());
        // Service 가 종료될 때 트랜잭션이 종료 > 더티체킹 > 자동 업데이트 (flush)
        return board;
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long id) {
        Board board = findByBoardId(id);
        boardRepository.deleteById(id);
    }

    // 게시글 찾기
    public Board findByBoardId(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("게시글을 찾을 수 없습니다 해당 ID : " + id);
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

    // 추천
    @Transactional
    public void voteUp(Long id, Principal principal) {
        Board board = findByBoardId(id);
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다 : " + principal.getName());
        });
        board.voteUp(user);
        boardRepository.save(board);
    }

    // 비추천
    @Transactional
    public void voteDown(Long id, Principal principal) {
        Board board = findByBoardId(id);
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다 : " + principal.getName());
        });
        board.voteDown(user);
        boardRepository.save(board);
    }

    // 게시글 검색 결과 리턴
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword,pageable);
    }

    // 게시글 조회수
    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }
}
