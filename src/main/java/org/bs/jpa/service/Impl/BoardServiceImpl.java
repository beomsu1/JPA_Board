package org.bs.jpa.service.Impl;

import java.util.Optional;

import org.bs.jpa.domain.Board;
import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.bs.jpa.repository.BoardRepository;
import org.bs.jpa.service.BoardService;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    // Create
    @Override
    public void boardCreate(BoardCreateDTO boardCreateDTO) {

        log.info("boardCreate ServiceImpl....");
        boardRepository.save(boardCreateDTO.toEntity());
    }

    // Read
    @Override
    public BoardDTO boardReadOne(Long bno) {

        log.info("boardReadOne ServiceImpl....");

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

        log.info("boardUpdate ServiceImpl....");

        Optional<Board> info =  boardRepository.findById(boardUpdateDTO.getBno());
        Board board = info.orElseThrow();

        boardUpdateDTO.boardUpdate(board);
        boardRepository.save(board);

    }

    // Delete
    @Override
    public void boardDelete(Long bno) {

        log.info("boardDelete ServiceImpl....");

        boardRepository.deleteById(bno);
    }

    // List
    @Override
    public PageResponseDTO<BoardListDTO> boardList(PageRequestDTO pageRequestDTO) {

        log.info("boardList ServiceImpl....");

        return boardRepository.boardList(pageRequestDTO);
    }
    
}
