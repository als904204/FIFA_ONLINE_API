package com.toy.fifa.Service;

import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.Reply;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.BoardRepository;
import com.toy.fifa.Repository.ReplyRepository;
import com.toy.fifa.Service.Community.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserService userService;



    @Test
    void 게시글_생성() {

        //WHEN
        Board b1 = new Board();
        b1.setTitle("제목ddddddddddddddddd");
        b1.setContent("내용ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        b1.setCreateDate(LocalDateTime.now());
        b1.setCount(50);

        User user = new User(1L, "user1ddddd", "pwd", "naver@naver");
//        User u1 = userService.join(user);
        User author = userService.findById(1L);
        //b1.setAuthor(author);

        Board board = boardRepository.save(b1);
        Long findBoard = boardRepository.findById(board.getId()).orElseThrow().getId();
        assertThat(findBoard).isEqualTo(board.getId());

        // 1번 유저가 첫번째로 작성한 게시글 isEqualTo 1번 게시글?
       // Optional<Board> getAuthor = boardRepository.findById(board.getAuthor().getId());
       // assertThat(u1.getId()).isEqualTo(getAuthor.get().getId());
    }


    @Test
    @Transactional
    void 게시글_조회() {

        //WHEN
        Board b1 = new Board();
        b1.setTitle("What is FIFA_Helper");
        b1.setContent("I want to know this web site");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);

        Board b2 = new Board();
        b2.setTitle("what's up guys");
        b2.setContent("hey");
        b2.setCreateDate(LocalDateTime.now());
        boardRepository.save(b2);

        List<Board> boardList = boardRepository.findAll();
        assertThat(boardList.size()).isEqualTo(2);

        Board b = boardList.get(0);
        assertThat("I want to know this web site").isEqualTo(b.getContent());
    }

    @Test
    @Transactional
    void 게시글_ID_조회() {
        Board b1 = new Board();
        b1.setTitle("What is FIFA_Helper");
        b1.setContent("I want to know this web site");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);

        Optional<Board> board = boardRepository.findById(b1.getId());

        if (board.isPresent()) {
            Board b = board.get();
            assertThat("What is FIFA_Helper").isEqualTo(b.getTitle());
        }
    }

    @Test
    @Transactional
    void 게시글_제목_조회() {
        Board b1 = new Board();
        b1.setTitle("What is FIFA_Helper");
        b1.setContent("I want to know this web site");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);
        Board b = boardRepository.findByTitle("What is FIFA_Helper");
        assertThat(1L).isEqualTo(b.getId());
    }

    @Test
    @Transactional
    void 게시글_제목_내용_조회() {
        Board b1 = new Board();
        b1.setTitle("What is FIFA_Helper");
        b1.setContent("I want to know this web site");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);

        Board board = boardRepository.findByTitleAndContent("What is FIFA_Helper", "I want to know this web site");
        assertThat(1L).isEqualTo(board.getId());
    }

    @Test
    @Transactional
    void 게시글_제목_LIKE_조회() {
        Board b1 = new Board();
        b1.setTitle("What is FIFA_Helper");
        b1.setContent("I want to know this web site");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);

        List<Board> board = boardRepository.findByTitleLike("What%");

        String getBoardTitle = board.get(0).getTitle();
        assertThat(b1.getTitle()).isEqualTo(getBoardTitle);
    }

    @Test
    @Transactional
    void 게시글_수정() {
        Board b1 = new Board();
        b1.setTitle("What is FIFA_Helper");
        b1.setContent("I want to know this web site");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);

        Optional<Board> board = boardRepository.findById(b1.getId());

        assertThat(board.isPresent());

        Board b = board.get();
        b.setTitle("Update Title");
        boardRepository.save(b);
    }

    @Test
    @Transactional
    void 게시글_삭제() {
        Board b1 = new Board();
        b1.setTitle("What is FIFA_Helper");
        b1.setContent("I want to know this web site");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);

        assertThat(1).isEqualTo(boardRepository.count());

        Optional<Board> board = boardRepository.findById(1L);
        assertThat(board.isPresent());

        Board deleteBoard = board.get();

        boardRepository.delete(deleteBoard);
        assertThat(0).isEqualTo(boardRepository.count());
    }

    @Test
    @Transactional
    void 댓글_생성() {
        Board b1 = new Board();
        b1.setTitle("What is FIFA_Helper");
        b1.setContent("I want to know this web site");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);

        Optional<Board> b = boardRepository.findById(b1.getId());

        Board board = b.get();

        

        Reply reply = new Reply();
        reply.setContent("댓글");
        reply.setBoard(board); // 어떤 게시글인지
        reply.setCreateDate(LocalDateTime.now());
        Reply r1 = replyRepository.save(reply);

        Optional<Reply> getReply = replyRepository.findById(r1.getId());
        assertThat(getReply.isPresent());
        Reply r = getReply.get();
        List<Reply> replyList = board.getReplyList();



        assertThat(1L).isEqualTo(r.getBoard().getId()); // 1번 게시글에 달린 댓글이 맞는지
    }

    @Test
    void 테스트_게시글_생성() {
        Board b1 = new Board();
        b1.setTitle("제목");
        b1.setContent("내용");
        b1.setCreateDate(LocalDateTime.now());
        boardRepository.save(b1);
    }

    @Test
    void 테스트_댓글_생성() {
        Optional<Board> ob = boardRepository.findById(1L);
        Board board = ob.get();

        Reply r = new Reply();
        r.setContent("댓글");
        r.setBoard(board);
        r.setCreateDate(LocalDateTime.now());
        replyRepository.save(r);
    }

    @Test
    @Transactional
    void 댓글로_게시글_찾기() {
        Optional<Board> ob = boardRepository.findById(1L);
        Board b = ob.get();

        List<Reply> replyList = b.getReplyList();
        assertThat(1).isEqualTo(replyList.size());
        assertThat(b.getReplyList().get(0).getContent()).isEqualTo(replyList.get(0).getContent());


    }



}