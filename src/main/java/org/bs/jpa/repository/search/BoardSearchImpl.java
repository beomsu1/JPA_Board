package org.bs.jpa.repository.search;

import org.bs.jpa.domain.Board;
import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    // 생성자를 만들어줘서 오류를 해결하자 - 컴파일 에러 해결
    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public PageResponseDTO<BoardListDTO> boardList(PageRequest pageRequest) {

        

        return null;

        
    }
    
}
