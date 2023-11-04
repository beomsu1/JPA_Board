package org.bs.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.bs.jpa.domain.Board;
import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    // BoardCreate
    @Test
    @DisplayName("게시판 등록")
    public void boardCreateRepositoryTest() {

        // Given
        log.info("board Create Repository Test Start");

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
        log.info("Board Create Repository Test Complete");
    }

    // BoardRead
    @Test
    @DisplayName("게시판 조회")
    public void boardReadRepositoryTest() {

        // Given
        log.info("board Read Repository Test Start");
        Long bno = 11L;

        // When
        Optional<Board> info = boardRepository.findById(bno); // 값이 없을수도 있어서 Optional 사용
        Board board = info.orElseThrow(); // 예외가 있다면 Throw

        // Then
        log.info(board);
        log.info("Board Read Repository Test Complete");

    }

    // BoardUpdate
    @Test
    @DisplayName("게시판 수정")
    public void boardUpdateRepositoryTest() {

        // given
        log.info("board Update Repository Test Start");

        Long bno = 1L;
        String newTitle = "안녕하세요 테스트입니다.";
        String newContent = "반갑습니다 테스트입니다.";

        BoardUpdateDTO boardUpdateDTO = BoardUpdateDTO.builder()
                .title(newTitle)
                .content(newContent)
                .build();

        // when

        // 조회
        Optional<Board> info = boardRepository.findById(bno); // 값이 없을수도 있어서 Optional 사용
        Board board = info.orElseThrow(); // 예외가 있다면 Throw

        boardUpdateDTO.boardUpdate(board);
        board = boardRepository.save(board);

        // Then
        assertEquals(bno, board.getBno());
        assertEquals(newTitle, board.getTitle());
        assertEquals(newContent, board.getContent());

        log.info("Board Update Repository Test Complete");
    }

    // BoardDelete
    @Test
    @DisplayName("게시판 삭제")
    public void boardDeleteRepositoryTest() {

        // Given
        log.info("board Delete Repository Test Start");

        Long bno = 2L;

        // When
        boardRepository.deleteById(bno);

        // Then
        log.info("Board Delete Repository Test Complete");

    }

    // BoardList
    @Test
    @DisplayName("게시판 리스트")
    public void boardListRepositoryTest() {

        // Given
        log.info("board List Repository Test Start");

        // when
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> page = boardRepository.findAll(pageable);

        // 리스트 담아주기
        List<Board>list = page.getContent();

        // then
        log.info("페이지: " + page);
        
        log.info("--------------------");

        log.info("리스트: " + list);

        log.info("Board List Repository Test Complete");
    }

}
