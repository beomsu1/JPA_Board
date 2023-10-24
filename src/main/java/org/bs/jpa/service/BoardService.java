package org.bs.jpa.service;

import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;

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
    
}
