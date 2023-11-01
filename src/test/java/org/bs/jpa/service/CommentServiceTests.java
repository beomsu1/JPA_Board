package org.bs.jpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.bs.jpa.domain.Comment;
import org.bs.jpa.dto.comment.CommentCreateDTO;
import org.bs.jpa.dto.comment.CommentDTO;
import org.bs.jpa.dto.comment.CommentUpdateDTO;
import org.bs.jpa.dto.comment.CommnetListDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CommentServiceTests {

    @Autowired
    private CommentService commentService;

    // Comment Create
    @Test
    @DisplayName("댓글 생성 테스트")
    public void commentCreateServiceTest() {

        // Given
        log.info("Comment Create Service Test Start");

        Long bno = 1L;
        String comments = "댓글 생성 서비스 테스트";
        String commenter = "작성자 테스트";

        CommentCreateDTO commentCreateDTO = CommentCreateDTO.builder()
                .bno(bno)
                .commnets(comments)
                .commenter(commenter)
                .build();

        // When
        commentService.commentCreate(commentCreateDTO);

        // Then
        assertEquals(bno, commentCreateDTO.getBno());
        assertEquals(comments, commentCreateDTO.getCommnets());
        assertEquals(commenter, commentCreateDTO.getCommenter());

        log.info("Comment Create Service Test Complete");
    }

    // Comment ReadOne
    @Test
    @DisplayName("댓글 조회 테스트")
    public void CommentReadOneServiceTest(){

        // Given
        log.info("Comment ReadOne Service Test Start");
        Long cno = 3L;

        // When
        CommentDTO commentDTO = commentService.commentReadOne(cno);

        // Then
        log.info(commentDTO.getCno()+ "번 댓글 조회: " +commentDTO.toString());
        log.info("Comment ReadOne Service Test Complete");

    }

    // Comment Update
    @Test
    @DisplayName("댓글 수정 테스트")
    public void commentUpdateServiceTest(){

        // Given
        log.info("Comment Update Service Test Start");

        Long cno = 2L;
        String comments = "수정된 댓글입니다.";
        String commenter = "범수";

        CommentUpdateDTO commentUpdateDTO = CommentUpdateDTO.builder()
        .cno(cno)
        .comments(comments)
        .commeters(commenter)
        .build();

        // When
        commentService.commentUpdate(commentUpdateDTO);

        // Then
        assertEquals(cno, commentUpdateDTO.getCno());
        assertEquals(comments, commentUpdateDTO.getComments());
        assertEquals(commenter, commentUpdateDTO.getCommeters());
        log.info("Commnet Update Service Test Complete");

    }

    // Comment Delete
    @Test
    @DisplayName("댓글 삭제 테스트")
    public void commentDeleteServiceTest(){

        // Given
        log.info("Comment Delete Service Test Start");
        Long cno = 3L;

        // When
        commentService.commentDelete(cno);

        // Then
        log.info("Comment Delete Service Test Completes");

    }

    // Commenet List
    @Test
    @DisplayName("댓글 목록 테스트")
    public void commnetListServiceTest(){

        // Given
        log.info("Commnet List Service Test Start");

        Long bno = 1L;
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        PageResponseDTO<CommnetListDTO> list  = commentService.commentList(bno, pageRequestDTO);
        List<CommnetListDTO> commentList = list.getDtoList();
        
        // Then
        for (CommnetListDTO commnetListDTO : commentList) {
            log.info("Commnet : " + commnetListDTO.toString());
            
        }
        log.info(list.toString());

        log.info("Comment List Service Test Complete");
    }

}
