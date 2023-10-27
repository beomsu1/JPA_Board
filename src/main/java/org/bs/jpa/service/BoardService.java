package org.bs.jpa.service;

import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.domain.PageRequest;

import jakarta.transaction.Transactional;

@Transactional
public interface BoardService {

    // Create
    void boardCreate (BoardCreateDTO boardCreateDTO);

    // Read
    BoardDTO boardReadOne (Long bno);

    // Update
    void boardUpdate (BoardUpdateDTO boardUpdateDTO);

    // Delete
    void boardDelete (Long bno);

    // List
    PageResponseDTO<BoardListDTO> boardList (PageRequest pageRequest);
    
}
