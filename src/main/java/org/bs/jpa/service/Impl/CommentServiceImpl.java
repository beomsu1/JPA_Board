package org.bs.jpa.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bs.jpa.domain.Board;
import org.bs.jpa.domain.Comment;
import org.bs.jpa.dto.comment.CommentCreateDTO;
import org.bs.jpa.dto.comment.CommentDTO;
import org.bs.jpa.dto.comment.CommentUpdateDTO;
import org.bs.jpa.dto.comment.CommnetListDTO;
import org.bs.jpa.repository.BoardRepository;
import org.bs.jpa.repository.CommentRepository;
import org.bs.jpa.service.CommentService;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    // Comment Create
    @Override
    public void commentCreate(CommentCreateDTO commentCreateDTO) {

        log.info("Comment Create ServiceImpl Start");

        // Board를 bno를 이용해 생성해주고 Comment 빌드
        Comment comment = Comment.builder()
        .board(Board.builder().bno(commentCreateDTO.getBno()).build())
        .comments(commentCreateDTO.getComments())
        .commenter(commentCreateDTO.getCommenter())
        .build();

        // Comment Entity Save
        commentRepository.save(comment);

    }

    // Comment ReadOne
    @Override
    public CommentDTO commentReadOne(Long cno) {

        log.info("Comment ReadOne ServiceImpl Start");

        Optional<Comment> info = commentRepository.findById(cno);
        Comment comment = info.orElseThrow();

        // Entity -> DTO 형변환
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.entityToDTO(comment);

        return commentDTO;
    }

    // Comment Update
    @Override
    public void commentUpdate(CommentUpdateDTO commentUpdateDTO) {

        log.info("Comment Update ServiceImpl Start");

        Optional<Comment> info = commentRepository.findById(commentUpdateDTO.getCno());
        Comment comment = info.orElseThrow();

        commentUpdateDTO.updateFlagTrue();

        comment.updateCommnent(commentUpdateDTO);
        commentRepository.save(comment);

    }

    // Comment Delete
    @Override
    public void commentDelete(Long cno) {

        log.info("Comment Delete ServiceImpl Start");

        Optional<Comment> info = commentRepository.findById(cno);
        Comment comment = info.orElseThrow();

        comment.deleteComment();
        commentRepository.save(comment);

    }

    // bno -> Comment List
    @Override
    public PageResponseDTO<CommnetListDTO> commentList(Long bno, PageRequestDTO pageRequestDTO) {

        log.info("Comment List ServiceImpl Start");

        // pagealbe 생성
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                Sort.by("cno").ascending());

        Page<Comment> info = commentRepository.getCommentList(bno, pageable);

        // total Count
        Long totalCoiunt = info.getTotalElements();

        // dtoList
        List<CommnetListDTO> dtoList = info.getContent().stream().map(comment -> CommnetListDTO.builder()
                .cno(comment.getCno())
                .commnets(comment.getComments())
                .commenter(comment.getCommenter())
                .regDate(comment.getRegDate())
                .build())
                .collect(Collectors.toList());

        return new PageResponseDTO<>(dtoList, totalCoiunt, pageRequestDTO);
    }

}
