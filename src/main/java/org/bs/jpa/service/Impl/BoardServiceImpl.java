package org.bs.jpa.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bs.jpa.domain.Board;
import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.bs.jpa.repository.BoardRepository;
import org.bs.jpa.service.BoardService;
import org.bs.jpa.util.FileuploadUtil;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final FileuploadUtil fileuploadUtil;

    // Create
    @Override
    public void boardCreate(BoardCreateDTO boardCreateDTO) {

        log.info("boardCreate ServiceImpl....");

        Board board = Board.builder()
                .title(boardCreateDTO.getTitle())
                .content(boardCreateDTO.getContent())
                .writer(boardCreateDTO.getWriter())
                .build();

        // files를 board에 filesave
        boardCreateDTO.getFiles().forEach(file -> {
            board.fileSave(file);
        });

        boardRepository.save(board);
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
    public Board boardUpdate(BoardUpdateDTO boardUpdateDTO) {

        log.info("boardUpdate ServiceImpl....");

        Optional<Board> info = boardRepository.findById(boardUpdateDTO.getBno());
        Board board = info.orElseThrow();

        log.info("before :" + board.toString());

        board.update(boardUpdateDTO);
        log.info(boardUpdateDTO);

        log.info("after :" + board.toString());
        log.info(board.getFiles());

        List<String> existingFile = board.getFiles().stream().map(file -> file.getFname()).collect(Collectors.toList());

        log.info("----------111111111111111"+ board.getFiles());

        board.fileClear();

        log.info("----------22222222222222222"+ board.getFiles());

        boardUpdateDTO.getFiles().forEach(file -> board.fileSave(file));

        log.info("----------33333333333333"+ board.getFiles());

        // 파일

        List<String> newFile = boardUpdateDTO.getFiles();

        List<String> deleteFile = existingFile.stream()
                .filter(file -> !newFile.contains(file))
                .collect(Collectors.toList());

        log.info("-------------------------------");
        log.info(deleteFile);

        log.info("-------------------------------");

        fileuploadUtil.delete(deleteFile);

        boardRepository.save(board);

        return board;

    }

    // Delete
    @Override
    public void boardDelete(Long bno) {

        log.info("boardDelete ServiceImpl....");

        Optional<Board> info = boardRepository.findById(bno);
        Board board = info.orElseThrow();

        List<String> file = board.getFiles().stream().map(f -> f.getFname()).collect(Collectors.toList());

        fileuploadUtil.delete(file);

        boardRepository.deleteById(bno);

    }

    // List
    @Override
    public PageResponseDTO<BoardListDTO> boardList(PageRequestDTO pageRequestDTO) {

        log.info("boardList ServiceImpl....");

        return boardRepository.boardList(pageRequestDTO);
    }

}
