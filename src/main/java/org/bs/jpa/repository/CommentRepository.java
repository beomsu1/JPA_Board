package org.bs.jpa.repository;

import org.bs.jpa.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    // 게시물에 대한 Comment List
    @Query("select c from Comment c where c.board.bno = :bno")
    Page<Comment> getCommentList (@Param("bno") Long bno , Pageable pageable);
}
