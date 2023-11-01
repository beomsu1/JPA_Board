package org.bs.jpa.dto.comment;

import org.bs.jpa.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {

    private Long bno;
    private Long cno;
    private String commnets;
    private String commenter;
    private String regDate;
    private boolean deleteFlag;
    private boolean updateFlag;


    // Entity -> DTO
    public void entityToDTO (Comment comment){
        this.bno = comment.board.getBno();
        this.cno = comment.getCno();
        this.commnets = comment.getComments();
        this.commenter = comment.getCommenter();
        this.regDate = comment.getRegDate();
        this.deleteFlag = comment.isDeleteFlag();
        this.updateFlag = comment.isUpdateFlag();
    }
}
