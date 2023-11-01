package org.bs.jpa.service;

import org.bs.jpa.dto.comment.CommentCreateDTO;
import org.bs.jpa.dto.comment.CommentDTO;
import org.bs.jpa.dto.comment.CommentUpdateDTO;
import org.bs.jpa.dto.comment.CommnetListDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

@Transactional
public interface CommentService {

    // Create
    void commentCreate(CommentCreateDTO commentCreateDTO);

    // Read
    CommentDTO commentReadOne (Long cno);

    // Update
    void commentUpdate (CommentUpdateDTO commentUpdateDTO);

    // Delete
    void commentDelete (Long cno);

    // List
    PageResponseDTO<CommnetListDTO> commentList (@Param("bno") Long bno, PageRequestDTO pageRequestDTO);
    
}
