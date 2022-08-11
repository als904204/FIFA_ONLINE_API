package com.toy.fifa.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm")
    private LocalDateTime createDate;

    private LocalDateTime modifyDate;


    private int count;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"}) // 댓글 무한참조 방지가 됨 == getter 호출을 막음
    @OrderBy("id desc") // Board를 부를 때, replys_id 기준으로 내림차순으로 정렬을함 - 즉 최근 댓글이 맨 위
    private List<Reply> replyList;


    @ManyToOne(fetch = FetchType.EAGER) // EAGER은 호출할 때 바로 로드하는 것임
    @JoinColumn(name="userId")
    private User author;

    // 추천 중복 X Set
    @ManyToMany(fetch = FetchType.EAGER)
    Set<User> voteUp;

    // 추천 중복 X Set
    @ManyToMany(fetch = FetchType.EAGER)
    Set<User> voteDown;

    public void voteUp(User user) {
        if (voteUp.contains(user)) {
            throw new EntityExistsException("해당유저가 해당게시글에 이미 추천을 했습니다");
        }else {
            getVoteUp().add(user);
        }
    }

    public void voteDown(User user) {
        if (voteDown.contains(user)) {
            throw new EntityExistsException("해당유저가 해당게시글에 이미 반대 하였습니다");
        }else {
            getVoteDown().add(user);
        }
    }


}
