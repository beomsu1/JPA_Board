package org.bs.jpa.dto.board;

import org.bs.jpa.domain.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardUpdateDTO {

    private Long bno;
    private String title;
    private String content;

    public void boardUpdate(Board board){

        board.update(title, content);
    }


}
