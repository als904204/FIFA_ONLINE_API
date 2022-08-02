package com.toy.fifa.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Reply {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE) // Board 테이블 조회할 때 유저 ID 값을 무조건 가져옴
    @JoinColumn(name = "userId")
    private User author;



}
