package org.bs.jpa.dto.board;

import org.bs.jpa.domain.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardCreateDTO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    
    // DTO -> Entity
    public Board toEntity(){

        Board board = Board.builder()
        .title(this.getTitle())
        .content(this.getContent())
        .writer(this.getWriter())
        .build();

        return board;
    }

}