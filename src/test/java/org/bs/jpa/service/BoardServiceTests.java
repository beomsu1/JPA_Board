package org.bs.jpa.service;

import org.bs.jpa.domain.Board;
import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;


    // Create
    @Test
    @DisplayName("게시물 등록 서비스")
    public void boardCreateServiceTest(){

        // Given
        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
        .title("sujung")
        .content("song")
        .writer("song su jung")
        .build();

        // When
        boardService.boardCreate(boardCreateDTO);

        // Then
        log.info("Board Create Complete");

    }

    // Read
    @Test
    @DisplayName("게시물 조회 서비스")
    public void boardReadServiceTest(){

        // Given
        Long bno = 3L;

        // When
        BoardDTO boardDTO = boardService.boardReadOne(bno);

        // Then
        log.info(boardDTO);

    }

    // Update
    @Test
    @DisplayName("게시물 수정 서비스")
    public void boardUpdateServiceTest(){

        // Given
        BoardUpdateDTO boardUpdateDTO = BoardUpdateDTO.builder()
        .bno(3L)
        .title("수정")
        .content("수정")
        .build();

        // When
       BoardDTO boardDTO = boardService.boardReadOne(boardUpdateDTO.getBno());

       // DTO -> Entity
       Board board = new Board();
       board.dtoTOEntity(boardDTO);

       boardUpdateDTO.boardUpdate(board);
       boardService.boardUpdate(boardUpdateDTO);

       // Then
       log.info("Board Update Service Complete");

    }
    

    // Delete
    @Test
    @DisplayName("게시물 삭제 서비스")
    public void boardDeleteServiceTest(){

        // Given
        Long bno = 106L;

        // When
        boardService.boardDelete(bno);

        // Then
        log.info("Board Delete Service Complete");
    }
}
