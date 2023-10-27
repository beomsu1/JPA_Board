package org.bs.jpa.repository.search;

import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.domain.PageRequest;

public interface BoardSearch {
    
    PageResponseDTO<BoardListDTO> boardList(PageRequest pageRequest);
}
