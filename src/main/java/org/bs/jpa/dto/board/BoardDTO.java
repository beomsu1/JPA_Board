package org.bs.jpa.dto.board;

import java.util.ArrayList;
import java.util.List;

import org.bs.jpa.domain.Board;
import org.bs.jpa.domain.Fileupload;

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
    private String regDate;
    private String modDate;

    @Builder.Default
    private List<Fileupload> files = new ArrayList<>();

    // Board(Entity) -> BoardDTO 
    public void BoardToBoardDTO(Board board){

        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.regDate = board.getRegDate();
        this.modDate = board.getModDate();
        this.files = board.getFiles();
    }

}
