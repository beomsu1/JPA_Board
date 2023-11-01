package org.bs.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.bs.jpa.domain.Board;
import org.bs.jpa.domain.Comment;
import org.bs.jpa.dto.comment.CommentCreateDTO;
import org.bs.jpa.dto.comment.CommentUpdateDTO;
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
public class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    // Comment Create
    @Test
    @DisplayName("댓글 생성 테스트")
    public void commentCreateRepositoryTest() {

        // Given
        log.info("Comment Create Repository Test Start");

        CommentCreateDTO commentCreateDTO = CommentCreateDTO.builder()
                .bno(1L)
                .comments("댓글 테스트")
                .commenter("작성자1")
                .build();

        Optional<Board> info = boardRepository.findById(commentCreateDTO.getBno());
        Board board = info.orElseThrow();

        // When
        Comment comment = Comment.builder()
                .board(board)
                .comments(commentCreateDTO.getComments())
                .commenter(commentCreateDTO.getCommenter())
                .build();

        commentRepository.save(comment);

        // Then
        log.info("Commnet Create Repository Test Complete");
    }

    // Commnet Reaed
    @Test
    @DisplayName("댓글 조회 테스트")
    public void commnetReadRepositoryTest() {

        // Given
        log.info("Commnet Read Repository Test Start");
        Long cno = 1L;

        // When
        Optional<Comment> info = commentRepository.findById(cno);
        Comment comment = info.orElseThrow();

        // Then
        log.info("Comment: " + comment.toString());
        log.info("Comment Read Repository Test Complete");

    }

    // Commnent update
    @Test
    @DisplayName("댓글 수정 테스트")
    public void commentUpdateRepositoryTest() {

        // Given
        log.info("Comment Update Repository Test Start");
        Long cno = 1L;
        String comments = "수정된 댓글입니다.";
        String commeter = "beomsu";

        CommentUpdateDTO commentUpdateDTO = CommentUpdateDTO.builder()
                .comments(comments)
                .commeters(commeter)
                .build();

        // When
        Optional<Comment> info = commentRepository.findById(cno);
        Comment comment = info.orElseThrow();

        commentUpdateDTO.updateFlagTrue();

        // Then
        comment.updateCommnent(commentUpdateDTO);
        commentRepository.save(comment);

        assertEquals(cno, comment.getCno());
        assertEquals(comments, comment.getComments());
        assertEquals(commeter, comment.getCommenter());

        log.info(comment.toString());
        log.info("Comment Update Repository Test Complete");
    }


    // Comment Delete
    @Test
    @DisplayName("댓글 삭제 테스트")
    public void commentDeleteRepositoryTest(){

        // Given
        log.info("Comment Delete Repository Test Start");
        Long cno = 1L;

        // When
        Optional<Comment> info = commentRepository.findById(cno);
        Comment comment = info.orElseThrow();

        comment.deleteComment();
        commentRepository.save(comment);

        // Then
        log.info("Comment Delete Repository Test Complete");
    }

    // Comment List
    @Test
    @DisplayName("댓글 목록 리스트 테스트")
    public void commentListTest() {

        // Given
        log.info("Comment List Repository Test Start");
        Long bno = 1L;

        // When
        Pageable pageable = PageRequest.of(0, 10, Sort.by("cno").ascending());

        Page<Comment> list = commentRepository.getCommentList(bno, pageable);

        // Then
        for (Comment comment : list) {
            log.info(comment.toString());
        }

        log.info("Comment List Repository Test Complete");

    }

}
