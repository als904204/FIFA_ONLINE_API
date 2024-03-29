package com.toy.fifa.Repository;

import com.toy.fifa.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByTitle(String title);

    Board findByTitleAndContent(String title, String content);

    List<Board> findByTitleLike(String title);

    Page<Board> findAll(Specification<Board> spec, Pageable pageable);

    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

    @Modifying
    @Query("update Board b set b.viewCount = b.viewCount + 1 where b.id= :id")
    int updateView(@Param("id") Long id);
}
