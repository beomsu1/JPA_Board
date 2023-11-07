package org.bs.jpa.service;

import java.util.List;

import org.bs.jpa.domain.Board;
import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    // Create
    @Test
    @Transactional
    @DisplayName("게시물 등록 서비스")
    public void boardCreateServiceTest() {

        // Given
        log.info("Board Create Service Test Start");

        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
                .title("test1")
                .content("test1")
                .writer("test")
                .build();

        // When
        boardService.boardCreate(boardCreateDTO);

        // Then
        log.info("Board Create Service Test Complete");

    }

    // Read
    @Test
    @DisplayName("게시물 조회 서비스")
    @Transactional
    public void boardReadServiceTest() {

        // Given
        log.info("Board Read Service Test Start");

        Long bno = 29L;

        // When
        BoardDTO boardDTO = boardService.boardReadOne(bno);

        // Then
        log.info(boardDTO);
        log.info("Board Read Service Test Complete");

    }

    // Update
    @Test
    @Transactional
    @DisplayName("게시물 수정 서비스")
    public void boardUpdateServiceTest() {

        // Given
        log.info("Board Update Service Test Start");

        BoardUpdateDTO boardUpdateDTO = BoardUpdateDTO.builder()
                .bno(1L)
                .title("update Title")
                .content("범수")
                .build();

        // When
        boardService.boardUpdate(boardUpdateDTO);

        // Then
        log.info("Board Update Service Test Complete");

    }

    // Delete
    @Test
    @Transactional
    @DisplayName("게시물 삭제 서비스")
    public void boardDeleteServiceTest() {

        // Given
        log.info("Board Delete Service Test Start");

        Long bno = 106L;

        // When
        boardService.boardDelete(bno);

        // Then
        log.info("Board Delete Service Test Complete");
    }

    // List
    @Test
    @DisplayName("게시물 목록 서비스")
    public void boardListServiceTest() {

        // Given
        log.info("Board List Service Test Start");

        String type = "tcw";
        String keyword = "test";

        // When
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .keyword(keyword)
                .type(type)
                .build();

        PageResponseDTO<BoardListDTO> list = boardService.boardList(pageRequestDTO);

        // Then
        log.info("리스트: " + list);
        log.info("Board List Service Test Complete");
    }

    // Board File Create
    @Test
    @Transactional
    @DisplayName("게시판 파일 추가")
    public void boardFileCreateServiceTest() {

        // Given
        log.info("Board File Create Service Test Start");

        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
                .title("test")
                .content("test")
                .writer("test")
                .files(List.of("test", "test1"))
                .build();

        // When
        boardService.boardCreate(boardCreateDTO);

        // Then
        log.info("Board File Create Service Test Complete");
    }

    // Board File Read
    @Test
    @Transactional
    @DisplayName("게시판 파일 조회")
    public void boardFileReadServiceTest(){

        // Given
        log.info("Board File Read Serivce Test Start");

        Long bno = 29L;

        // When
        BoardDTO boardDTO = boardService.boardReadOne(bno);

        // Then
        log.info(boardDTO.toString());
        log.info("Board File Read Service Test Complete");
    }

    // Board File Update
    @Test
    @Transactional
    @DisplayName("게시판 파일 수정")
    public void boardFileUpdateServiceTest(){

        // Given
        log.info("Board File Update Service Test Start");

        BoardUpdateDTO boardUpdateDTO = BoardUpdateDTO.builder()
        .bno(29L)
        .title("update Title")
        .content("update Content")
        .files(List.of("update file1","update file2","new file1"))
        .build();

        // When
        Board board = boardService.boardUpdate(boardUpdateDTO);

        // Then
        log.info(board.toString());
        log.info(board.getFiles());
        log.info("Board File Update Service Test Complete");
    }
}
