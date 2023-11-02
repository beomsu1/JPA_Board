package org.bs.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.bs.jpa.domain.Board;
import org.bs.jpa.domain.Fileupload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FileuploadRepository {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("파일 등록 테스트")
    public void fileCreateRepositoryTest(){

        // Given
        log.info("File Create Repository Test Start");

        Board board = Board.builder()
        .content("file uplaod test")
        .writer("beomsu")
        .build();

        Fileupload file1 = Fileupload.builder()
        .uuid(UUID.randomUUID().toString())
        .fname("file1")
        .build();

        Fileupload file2 = Fileupload.builder()
        .uuid(UUID.randomUUID().toString())
        .fname("file2")
        .build();

        // When
        board.imageSave(file1);
        board.imageSave(file2);

        boardRepository.save(board);

        // Then
        assertEquals(file1, board.getFiles().get(0));
        assertEquals(file2, board.getFiles().get(1));

        log.info("File Create Repository Test Complete");
        
    }
    
}
