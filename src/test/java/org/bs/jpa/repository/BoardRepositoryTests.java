package org.bs.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.bs.jpa.domain.Board;
import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    // BoardCreate
    @Test
    @DisplayName("게시판 등록")
    public void boardCreateTest() {

        // Given
        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
                .title("테스트입니다.")
                .content("테스트 내용입니다.")
                .writer("beomsu1")
                .build();

        // When
        Board board = boardRepository.save(boardCreateDTO.toEntity());

        // Then
        assertNotNull(board); // 게시물 notNull 확인
        assertEquals(board.getTitle(), boardCreateDTO.getTitle());
        log.info("Board Create Complete");
    }

    // BoardRead
    @Test
    @DisplayName("게시판 조회")
    public void boardReadTest() {

        // Given
        Long bno = 11L;

        // When
        Optional<Board> info = boardRepository.findById(bno); // 값이 없을수도 있어서 Optional 사용
        Board board = info.orElseThrow(); // 예외가 있다면 Throw

        // Then
        log.info(board);

    }

    // BoardUpdate
    @Test
    @DisplayName("게시판 수정")
    public void boardUpdateTest() {

        // given
        Long bno = 4L;
        String newTitle = "안녕하세요 테스트입니다.";
        String newContent = "반갑습니다.";

        BoardUpdateDTO boardUpdateDTO = BoardUpdateDTO.builder()
        .title(newTitle)
        .content(newContent)
        .build();

        Optional<Board> info = boardRepository.findById(bno); // 값이 없을수도 있어서 Optional 사용
        Board board = info.orElseThrow(); // 예외가 있다면 Throw

        // when
        boardUpdateDTO.BoardUpdate(board);
        board = boardRepository.save(board);

        // Then
        assertEquals(bno, board.getBno());
        assertEquals(newTitle, board.getTitle());
        assertEquals(newContent, board.getContent());

        log.info("Board Update Complete");
    }

    // BoardDelete
    @Test
    @DisplayName("게시판 삭제")
    public void boardDeleteTest(){

        // Given
        Long bno = 2L;

        // When
        boardRepository.deleteById(bno);

        // Then
        log.info("Board Delete Complete");

    }

}
