package org.bs.jpa.service;

import java.util.Optional;

import org.bs.jpa.domain.Board;
import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.bs.jpa.repository.BoardRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    // Create
    @Override
    public void boardCreate(BoardCreateDTO boardCreateDTO) {
        boardRepository.save(boardCreateDTO.toEntity());
    }

    // Read
    @Override
    public BoardDTO boardReadOne(Long bno) {

        Optional<Board> info = boardRepository.findById(bno);

        Board board = info.orElseThrow();

        // Entity -> DTO
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.BoardToBoardDTO(board);

        return boardDTO;

    }

    // Update
    @Override
    public void boardUpdate(BoardUpdateDTO boardUpdateDTO) {

        Optional<Board> info =  boardRepository.findById(boardUpdateDTO.getBno());
        Board board = info.orElseThrow();

        boardUpdateDTO.BoardUpdate(board);
        boardRepository.save(board);

    }

    // Delete
    @Override
    public void boardDelete(Long bno) {

        boardRepository.deleteById(bno);
    }
    
}
