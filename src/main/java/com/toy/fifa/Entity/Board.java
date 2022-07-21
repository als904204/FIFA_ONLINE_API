package com.toy.fifa.Entity;


import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Board {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 200)
    private String title;

    @Lob // 대용량
    private String content;

    private LocalDateTime createDate;

    private int count;

    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Reply> replyList;



//    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE) // Board 테이블 조회할 때 유저 ID 값을 무조건 가져옴
//    @JoinColumn(name = "userId")
//    private User author;

}
