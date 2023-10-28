package org.bs.jpa.dto.board;

import lombok.Data;

@Data
public class BoardListDTO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    private String regDate;
    private String modDate;
    
}
