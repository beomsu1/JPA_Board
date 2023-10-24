package org.bs.jpa.dto.board;

import java.time.LocalDateTime;

import org.bs.jpa.domain.Board;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private String writer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;

    // Board(Entity) -> BoardDTO 
    public void BoardToBoardDTO(Board board){

        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.regDate = board.getRegDate();
        this.modDate = board.getModDate();
    }

}
