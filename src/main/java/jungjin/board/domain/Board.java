package jungjin.board.domain;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jungjin.user.domain.User;

@Getter
@Entity
@Data
@Table(name="board")
public class Board implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_board_writer"))
    private User user;

    @NotNull
    @Column(name="title", length=500)
    private String title;

    @Lob
    private String contents;

    //S:사용중 D:삭제
    @NotNull
    @Column(name="status")
    private String status;

    @NotNull
    @Column(name="create_date")
    private LocalDateTime createDate;

    @Column(name="read_count")
    private int readcount;

    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_board_master_id"))
    private BoardMaster boardMaster;

    //@Transient
    @OneToMany
    @JoinColumn(name = "board_id")
    List<BoardReply> boardReplyList;

    public void update(Board upateBoard){
        this.title = upateBoard.title;
        this.contents = upateBoard.contents;
    }

    public void insert(Board insertBoard){
        this.title = insertBoard.title;
        this.contents = insertBoard.contents;
        this.status="S";
        this.createDate = LocalDateTime.now();
    }

}
