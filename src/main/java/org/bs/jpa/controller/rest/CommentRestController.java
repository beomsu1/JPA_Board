package org.bs.jpa.controller.rest;

import org.bs.jpa.dto.comment.CommentCreateDTO;
import org.bs.jpa.dto.comment.CommentDTO;
import org.bs.jpa.dto.comment.CommentUpdateDTO;
import org.bs.jpa.dto.comment.CommnetListDTO;
import org.bs.jpa.service.CommentService;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/comment/")
public class CommentRestController {

    private final CommentService commentService;

    // Comment List
    @GetMapping("{bno}/list")
    public PageResponseDTO<CommnetListDTO> commentList (@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO){

        log.info("Comment List Controller Start");
        
        return commentService.commentList(bno, pageRequestDTO);
    }

    // Comment Create
    @PostMapping("")
    public String commentCreate (@RequestBody CommentCreateDTO commentCreateDTO){

        log.info("Comment Create Controller Start");

        commentService.commentCreate(commentCreateDTO);

        return "댓글 생성 완료";
    }

    // Comment ReadOne
    @GetMapping("{cno}")
    public CommentDTO commentReadOne (@PathVariable("cno") Long cno){

        log.info("Comment ReadOne Controller Start");

        return commentService.commentReadOne(cno);
    }

    // Comment Update
    @PutMapping("{cno}")
    public String commentUpdate (CommentUpdateDTO commentUpdateDTO){

        log.info("Comment Update Controller Start");

        commentService.commentUpdate(commentUpdateDTO);

        return "댓글 수정 완료";
    }

    // Comment Delete
    @DeleteMapping("{cno}")
    public String commentDelete (@PathVariable("cno") Long cno){

        log.info("Comment Delete Controller Start");

        commentService.commentDelete(cno);

        return "댓글 삭제 완료";
    }
    
}
